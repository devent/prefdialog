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
package com.anrisoftware.prefdialog.fields.filechooser

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserBean.*
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.*
import static java.util.regex.Pattern.compile

import java.awt.Container

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FileChooserField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileChooserTest extends FieldTestUtils {

	@Test
	void "manually"() {
		def title = "$NAME :: manually"
		def fieldName = INITIAL_VALUE
		def field = factory.create(container, bean, fieldName)
		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			Thread.sleep 60*1000
		})
	}

	@Test
	void "null value"() {
		def title = "$NAME :: null value"
		def fieldName = NULL_VALUE
		shouldFailWith(ReflectionError) {
			def field = factory.create(container, bean, fieldName)
		}
	}

	@Test
	void "no model"() {
		def title = "$NAME :: no model"
		def fieldName = NO_MODEL
		shouldFailWith(IllegalArgumentException) {
			def field = factory.create(container, bean, fieldName)
		}
	}

	@Test
	void "with initial value"() {
		def title = "$NAME :: with initial value"
		def fieldName = INITIAL_VALUE
		def field = factory.create(container, bean, fieldName)

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fixture.textBox "$fieldName-$FILE_FIELD_NAME" requireText compile(/.*aaa.txt/)
		})
	}

	@Test
	void "restore input"() {
		def title = "$NAME :: restore input"
		def fieldName = INITIAL_VALUE
		def field = factory.create(container, bean, fieldName)
		def tmpfileA = File.createTempFile("$NAME", null)
		def tmpfileB = File.createTempFile("$NAME", null)
		def fileField
		def openFileChooser

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			fileField = fixture.textBox "$fieldName-$FILE_FIELD_NAME"
			openFileChooser = fixture.button "$fieldName-$OPEN_FILE_CHOOSER_NAME"
		}, { FrameFixture fixture ->
			fileField.selectAll()
			fileField.enterText tmpfileA.absolutePath
			openFileChooser.focus()
			assert bean.initialValue == tmpfileA
		}, { FrameFixture fixture ->
			field.restoreInput()
			openFileChooser.focus()
			fileField.requireText compile(/.*aaa.txt/)
		})
	}

	static Injector injector

	static FileChooserFieldFactory factory

	static final String NAME = FileChooserTest.class.simpleName

	FileChooserBean bean

	Container container

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new AnnotationsModule(), new BeansModule(), new FileChooserModule())
		factory = injector.getInstance FileChooserFieldFactory
	}

	@Before
	void setupBean() {
		bean = new FileChooserBean()
		container = new JPanel()
	}
}