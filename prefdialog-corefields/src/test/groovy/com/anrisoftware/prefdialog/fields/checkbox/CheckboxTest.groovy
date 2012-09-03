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
	void "checkbox no text"() {
		factory.create(container, bean, CHECKBOX_NO_TEXT_FIELD).createField()
		beginPanelFrame title, container, {
			assert bean.checkboxNoText == false
			fixture.checkBox("$CHECKBOX_NO_TEXT").requireText(CHECKBOX_NO_TEXT)
		}, {
			fixture.checkBox("$CHECKBOX_NO_TEXT").click()
			assert bean.checkboxNoText == true
		}, {
			fixture.checkBox("$CHECKBOX_NO_TEXT").click()
			assert bean.checkboxNoText == false
		}
	}
}
