package com.globalscalingsoftware.prefdialog.internal.inputfield

import java.util.List;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.fields.Group 
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceDialogTest;
import com.globalscalingsoftware.prefdialog.validators.NotEmptyString 

class DialogGroupsTest extends AbstractPreferenceDialogTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@TextField(validator=NotEmptyString, validatorText="Must not be empty")
		String name = ""
		
		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4
		
		@Group
		Group1 group1 = new Group1()
		
		@Group
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
		
		@ComboBox("combobox1")
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
		window.textBox("name").enterText "name"
		window.textBox("fields").enterText "10"
		window.checkBox("automaticSave").click()
		window.radioButton("colors-BLUE").click()
		window.comboBox("comboBox").selectItem 1
		window.button("ok").click()
		
		assert preferences.general.name == "name"
		assert preferences.general.fields == 10
		assert preferences.general.automaticSave == true
		assert preferences.general.colors == Colors.BLUE
		assert preferences.general.comboBox == "second element"
	}
}
