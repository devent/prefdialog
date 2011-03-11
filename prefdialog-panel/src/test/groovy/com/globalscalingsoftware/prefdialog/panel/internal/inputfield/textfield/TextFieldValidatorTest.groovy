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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField 
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest;
import com.globalscalingsoftware.prefdialog.validators.Validator;

class TextFieldValidatorTest extends AbstractPreferencePanelTest {
	
	static class StringValidator implements Validator<String> {
		
		public boolean isValid(String value) {
			value != null && !value.trim().isEmpty()
		}
	}
	
	static class General {
		
		@TextField(validator=StringValidator, validatorText="Can not be empty")
		String name = ""
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	def setupPreferences() {
		preferences = new Preferences()
		panelName = "General"
	}
	
	@Test
	void testEnterInvalidTextAndApply() {
		fixture.textBox("name").enterText ""
		assert fixture.label("label-name").text() == "name (Can not be empty): "
	}
	
	@Test
	void testEnterValidTextAndApply() {
		fixture.textBox("name").enterText "test"
		fixture.panel("general").button("apply").click()
		assert preferences.general.name == "test"
	}
}
