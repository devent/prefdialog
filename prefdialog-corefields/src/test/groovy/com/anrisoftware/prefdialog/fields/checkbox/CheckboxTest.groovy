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
package com.anrisoftware.prefdialog.fields.checkbox

import static com.anrisoftware.prefdialog.fields.checkbox.CheckboxBean.*

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError
import com.google.inject.Injector

/**
 * Test the {@link CheckboxField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class CheckboxTest extends FieldTestUtils {

	static final title = "Check-Box Test"

	CheckboxFieldFactory factory

	CheckboxBean bean

	JPanel container

	@Before
	void beforeTest() {
		super.beforeTest()
		factory = injector.getInstance CheckboxFieldFactory
		bean = new CheckboxBean()
		container = new JPanel()
	}

	Injector createInjector() {
		def parent = super.createInjector()
		parent.createChildInjector new CheckboxModule()
	}

	@Test
	void "checkbox null value"() {
		shouldFailWith(ReflectionError) {
			factory.create(container, bean, NULL_VALUE_FIELD).createField()
		}
	}

	@Test
	void "apply user input"() {
		def field = factory.create(container, bean, NO_TEXT_FIELD).createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(NO_TEXT)
			assert bean.noText == false
		}, {
			checkBox.click()
			field.applyInput()
			assert bean.noText == true
		}, {
			checkBox.click()
			field.applyInput()
			assert bean.noText == false
		}
	}

	@Test
	void "restore user input"() {
		def field = factory.create(container, bean, NO_TEXT_FIELD).createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(NO_TEXT)
			assert bean.noText == false
		}, {
			checkBox.click()
			field.restoreInput()
			checkBox.requireNotSelected()
			assert bean.noText == false
		}, {
			checkBox.click()
			field.restoreInput()
			checkBox.requireNotSelected()
			assert bean.noText == false
		}
	}

	@Test
	void "checkbox no text"() {
		factory.create(container, bean, NO_TEXT_FIELD).createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(NO_TEXT)
			checkBox.requireText(NO_TEXT)
		}, { checkBox.click() }, { checkBox.click() }
	}

	@Test
	void "checkbox with text"() {
		factory.create(container, bean, WITH_TEXT_FIELD).createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(WITH_TEXT)
			checkBox.requireText("Checkbox Text")
		}, { checkBox.click() }, { checkBox.click() }
	}

	@Test
	void "checkbox with text resource"() {
		def field = factory.create(container, bean, WITH_TEXT_RESOURCE_FIELD).
						withTextsResource(createTextsResource()).
						createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(WITH_TEXT_RESOURCE)
			checkBox.requireText("Checkbox English")
		}, {
			field.locale = Locale.GERMAN
			checkBox.requireText("Checkbox Deutsch")
		}, {
			field.locale = Locale.ENGLISH
			checkBox.requireText("Checkbox English")
		}
	}

	@Test
	void "checkbox not showing text"() {
		def field = factory.create(container, bean, NOT_SHOW_TEXT_FIELD).
						createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(NOT_SHOW_TEXT)
			checkBox.requireText("")
		}, {
			field.showText = true
			checkBox.requireText(NOT_SHOW_TEXT)
		}, {
			field.showText = false
			checkBox.requireText("")
		}
	}

	@Test
	void "checkbox read only"() {
		factory.create(container, bean, READ_ONLY_FIELD).createField()
		def checkBox
		beginPanelFrame title, container, {
			checkBox = fixture.checkBox(READ_ONLY)
			checkBox.requireDisabled()
		}
	}
}
