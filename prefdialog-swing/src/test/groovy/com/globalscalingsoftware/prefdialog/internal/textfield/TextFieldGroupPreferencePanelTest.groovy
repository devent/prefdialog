package com.globalscalingsoftware.prefdialog.internal.textfield

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.Group 
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 

class TextFieldGroupPreferencePanelTest extends AbstractPreferenceTest {
	
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
	
	static class General {
		
		@TextField
		String preGroup = ""
		
		@Group
		Group1 group1 = new Group1()
		
		@Group
		Group2 group2 = new Group2()
		
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
		preferencesClass = Preferences
		preferences = new Preferences()
		preferencesParentName = "general"
		preferencesParentValue = preferences.general
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		window.textBox("textField1").enterText "test1"
		window.textBox("textField2").enterText "test2"
		window.textBox("textField3").enterText "test3"
		window.textBox("textField4").enterText "test4"
		window.panel("general").button("apply").click()
		
		assert window.label("label-textField1").text() == "textField1: "
		assert preferences.general.group1.textField1 == "test1"
		assert preferences.general.group1.textField2 == "test2"
		assert preferences.general.group2.textField3 == "test3"
		assert preferences.general.group2.textField4 == "test4"
	}
}
