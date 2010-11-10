package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.Validator 
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

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
		preferencesClass = Preferences
		preferences = new Preferences()
		preferencesParentName = "general"
		preferencesParentValue = preferences.general
	}
	
	@Test
	void testPanelInvalidTextClickApplyAndClose() {
		window.textBox("name").enterText ""
		assert window.label("label-name").text() == "name (Can not be empty): "
	}
	
	@Test
	void testPanelValidTextClickApplyAndClose() {
		window.textBox("name").enterText "test"
		window.panel("general").button("apply").click()
		assert preferences.general.name == "test"
	}
}
