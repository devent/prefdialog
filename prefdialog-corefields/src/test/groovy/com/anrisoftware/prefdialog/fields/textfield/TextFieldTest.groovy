/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.textfield

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Container
import java.awt.event.KeyEvent

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link TextField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TextFieldTest {

	@Test
	void "null value"() {
		def title = "TextFieldTest :: null value"
		def fieldName = NULL_VALUE
		def field = factory.create(container, bean, fieldName)
		def text = "Text A"
		def textB = "Text B"

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName requireVisible()
			fixture.textBox fieldName requireText("")
		}, { FrameFixture fixture ->
			fixture.textBox fieldName enterText text
			fixture.textBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			assert bean.nullValue == text
		}, { FrameFixture fixture ->
			fixture.textBox fieldName deleteText()
			fixture.textBox fieldName enterText textB
			fixture.textBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			field.restoreInput()
			fixture.textBox fieldName requireText text
			assert bean.nullValue == text
		})
	}

	@Test
	void "with initial value"() {
		def title = "TextFieldTest :: with initial value"
		def fieldName = INITIAL_VALUE
		def field = factory.create(container, bean, fieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName requireVisible()
			fixture.textBox fieldName requireText("Text")
		})
	}

	@Test
	void "not editable"() {
		def title = "TextFieldTest :: not editable"
		def fieldName = NOT_EDITABLE
		def field = factory.create(container, bean, fieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName requireVisible()
			fixture.textBox fieldName requireNotEditable()
			fixture.textBox fieldName requireText("Not Editable")
		}, { FrameFixture fixture ->
			field.setEditable true
			fixture.textBox fieldName requireEditable()
		}, { FrameFixture fixture ->
			field.setEditable false
			fixture.textBox fieldName requireNotEditable()
		})
	}

	@Test
	void "validated"() {
		def title = "TextFieldTest :: validated"
		def fieldName = VALIDATED
		def field = factory.create(container, bean, fieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName requireVisible()
			fixture.textBox fieldName requireText("")
		}, { FrameFixture fixture ->
			fixture.textBox fieldName enterText VALIDATED_VALID_VALUE
			fixture.textBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			fixture.textBox fieldName requireText VALIDATED_VALID_VALUE
			assert bean.validated == VALIDATED_VALID_VALUE
		}, { FrameFixture fixture ->
			fixture.textBox fieldName deleteText()
			fixture.textBox fieldName enterText VALIDATED_INVALID_VALUE
			fixture.textBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			fixture.textBox fieldName requireText VALIDATED_INVALID_VALUE
			assert bean.validated == VALIDATED_VALID_VALUE
		})
	}

	static Injector injector

	static TextFieldFactory factory

	static Texts texts

	TextFieldBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new AnnotationsModule(), new BeansModule(), new TextFieldModule())
		factory = injector.getInstance TextFieldFactory
		texts = createTextsResource(injector)
	}

	@Before
	void setupBean() {
		bean = new TextFieldBean()
		container = new JPanel()
	}
}
