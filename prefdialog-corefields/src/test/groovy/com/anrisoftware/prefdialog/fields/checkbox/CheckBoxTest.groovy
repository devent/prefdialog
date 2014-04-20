/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.checkbox

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.checkbox.CheckBoxBean.*
import static com.anrisoftware.prefdialog.fields.checkbox.CheckBoxService.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link CheckboxField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CheckBoxTest {

	@Test
	void "null value"() {
		def fieldName = NULL_VALUE
		shouldFailWith(ReflectionError) {
			def field = factory.create(bean, fieldName)
		}
	}

	@Test
	void "apply user input"() {
		def title = "$NAME::apply user input"
		def fieldName = NO_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireNotSelected()
		}, { FrameFixture fixture ->
			fixture.checkBox fieldName click()
			assert bean.noText == true
		}, { FrameFixture fixture ->
			fixture.checkBox fieldName click()
			assert bean.noText == false
		})
	}

	@Test
	void "restore user input"() {
		def title = "$NAME::restore user input"
		def fieldName = NO_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireNotSelected()
		}, { FrameFixture fixture ->
			fixture.checkBox fieldName click()
			field.restoreInput()
			fixture.checkBox fieldName requireNotSelected()
			assert bean.noText == false
		}, { FrameFixture fixture ->
			fixture.checkBox fieldName click()
			field.restoreInput()
			fixture.checkBox fieldName requireNotSelected()
			assert bean.noText == false
		})
	}

	@Test
	void "no text"() {
		def title = "$NAME::no text"
		def fieldName = NO_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireText(NO_TEXT)
		})
	}

	@Test
	void "with text"() {
		def title = "$NAME::with text"
		def fieldName = WITH_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireText("Checkbox Text")
		})
	}

	@Test
	void "with text resource"() {
		def title = "$NAME::with text resource"
		def fieldName = WITH_TEXT_RESOURCE
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		field.setTexts texts

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireText("Checkbox English")
		}, { FrameFixture fixture ->
			field.setLocale Locale.GERMAN
			fixture.checkBox fieldName requireText("Checkbox Deutsch")
		}, { FrameFixture fixture ->
			field.setLocale Locale.ENGLISH
			fixture.checkBox fieldName requireText("Checkbox English")
		})
	}

	@Test
	void "not showing text"() {
		def title = "$NAME::not showing text"
		def fieldName = NOT_SHOW_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireText("")
		}, { FrameFixture fixture ->
			field.setShowText true
			fixture.checkBox fieldName requireText(fieldName)
		}, { FrameFixture fixture ->
			field.setShowText false
			fixture.checkBox fieldName requireText("")
		})
	}

	@Test
	void "read only"() {
		def title = "$NAME::read only"
		def fieldName = READ_ONLY
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.checkBox fieldName requireDisabled()
		}, { FrameFixture fixture ->
			field.setEnabled true
			fixture.checkBox fieldName requireEnabled()
		}, { FrameFixture fixture ->
			field.setEnabled false
			fixture.checkBox fieldName requireDisabled()
		})
	}

	//@Test(timeout = 60000l)
	void "manually"() {
		def title = "$NAME::manually"
		def fieldName = NO_TEXT
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({
			Thread.sleep 60 * 1000l
			assert false : "manually test"
		})
	}

	static final String NAME = CheckBoxTest.class.simpleName

	static Injector injector

	static CheckBoxFieldFactory factory

	static Texts texts

	CheckBoxBean bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(new CoreFieldComponentModule(), new CheckBoxModule())
		factory = injector.getInstance CheckBoxFieldFactory
		texts = createTextsResource(injector)
	}

	@Before
	void setupBean() {
		bean = new CheckBoxBean()
	}
}
