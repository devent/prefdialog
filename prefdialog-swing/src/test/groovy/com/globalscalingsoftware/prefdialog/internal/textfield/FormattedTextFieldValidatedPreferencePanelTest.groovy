package com.globalscalingsoftware.prefdialog.internal.textfield
import com.globalscalingsoftware.prefdialog.Validator;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class FormattedTextFieldValidatedPreferencePanelTest extends AbstractPreferencePanelTest {
	
	static class FieldsValidator implements Validator<Integer> {
		public boolean isValid(Integer value) {
			value >= 2 && value <= 100
		}
	}
	
	static class General {
		
		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4
		
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
		window.textBox("fields").enterText "1"
		assert window.label("label-fields").text() == "fields (Must be a number and between 2 and 100): "
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		window.textBox("fields").enterText "10"
		window.panel("general").button("apply").click()
		assert preferences.general.fields == 10
	}
}
