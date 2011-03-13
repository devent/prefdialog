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
package com.globalscalingsoftware.prefdialog.dialog.internal

import java.util.List;

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child 
import com.globalscalingsoftware.prefdialog.annotations.ComboBox 
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField 
import com.globalscalingsoftware.prefdialog.annotations.Group 
import com.globalscalingsoftware.prefdialog.annotations.RadioButton 
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.validators.NotEmptyString 

class DialogGroupNameTest extends AbstractPreferenceDialogFixture {
	
	static class Preferences {
		
		@Child("General")
		General general = new General()
	}
	
	static class General {
		
		@TextField(validator=NotEmptyString, validatorText="Must not be empty")
		String name = ""
		
		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4
		
		@Group("Group One")
		Group1 group1 = new Group1()
		
		@Group("Group Two")
		Group2 group2 = new Group2()
		
		@Checkbox
		boolean automaticSave = false
		
		@RadioButton(columns=2)
		Colors colors = Colors.BLACK
		
		@ComboBoxElements("combobox1")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]
		
		@ComboBox(value="combobox1", elements="combobox1")
		String comboBox
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	static class Group1 {
		
		@TextField
		String textField1 = ""
		
		@TextField
		String textField2 = ""
	}
	
	static class Group2 {
		
		@TextField
		String textField3 = ""
		
		@TextField
		String textField4 = ""
	}
	
	def setupPreferences() {
		preferences = new Preferences()
	}
	
	@Test
	void testClickOkAndClose() {
		fixture.textBox("name").enterText "name"
		fixture.textBox("fields").deleteText()
		fixture.textBox("fields").enterText "10"
		fixture.textBox("textField1").enterText "field1"
		fixture.textBox("textField2").enterText "field2"
		fixture.textBox("textField3").enterText "field3"
		fixture.textBox("textField4").enterText "field4"
		fixture.checkBox("automaticSave").click()
		fixture.radioButton("colors-BLUE").click()
		fixture.comboBox("comboBox").selectItem 1
		fixture.button("ok").click()
		
		assert preferences.general.name == "name"
		//assert preferences.general.fields == 10
		assert preferences.general.automaticSave == true
		assert preferences.general.colors == Colors.BLUE
		assert preferences.general.comboBox == "second element"
		assert preferences.general.group1.textField1 == "field1"
		assert preferences.general.group1.textField2 == "field2"
		assert preferences.general.group2.textField3 == "field3"
		assert preferences.general.group2.textField4 == "field4"
	}
}
