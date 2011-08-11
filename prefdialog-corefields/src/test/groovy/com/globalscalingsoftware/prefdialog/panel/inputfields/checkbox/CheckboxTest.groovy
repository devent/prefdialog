/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Checkbox
import com.globalscalingsoftware.prefdialog.panel.inputfields.AbstractFieldFixture

class CheckboxTest extends AbstractFieldFixture {

	static factory = injector.getInstance(CheckBoxFieldHandlerFactory)

	static class General {

		@Checkbox
		def automaticSave = false
	}

	CheckboxTest() {
		super(new General(), 'automaticSave', factory)
	}

	@Test
	void "click and apply input"() {
		fixture.checkBox("automaticSave").click()
		inputField.applyInput parentObject
		assert parentObject.automaticSave == true
	}

	@Test
	void "click and restore input"() {
		fixture.checkBox("automaticSave").click()
		inputField.restoreInput parentObject
		assert parentObject.automaticSave == false
	}
}
