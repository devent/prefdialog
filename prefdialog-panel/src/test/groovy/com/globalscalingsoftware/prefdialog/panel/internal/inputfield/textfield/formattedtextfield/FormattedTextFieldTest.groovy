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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield


import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest;

class FormattedTextFieldTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@FormattedTextField
		double fields = 4
		
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
	void testComponents() {
		fixture.label("label-fields").requireVisible()
		assert fixture.label("label-fields").text() == "fields: "
		fixture.textBox("fields").requireVisible()
		assert fixture.textBox("fields").text() == "4"
		fixture.button("apply").requireVisible()
		assert fixture.button("apply").text() == "Apply "
		fixture.button("restore").requireVisible()
		assert fixture.button("restore").text() == "Restore"
	}
	
	@Test
	void testEnterTextAndApply() {
		fixture.textBox("fields").deleteText()
		fixture.textBox("fields").enterText "10"
		fixture.panel("general").button("apply").click()
		assert preferences.general.fields == 10
	}
	
	@Test
	void testEnterTextAndRestore() {
		fixture.textBox("fields").enterText "10"
		fixture.panel("general").button("restore").click()
		assert preferences.general.fields == 4
	}
}
