package com.globalscalingsoftware.prefdialog.internal.textfield
import com.globalscalingsoftware.prefdialog.Validator;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class FormattedTextFieldValidatedPreferencePanelTest extends AbstractPreferenceTest {
	
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
	
	def preferences
	
	def parentValue
	
	def field
	
	def injector
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		parentValue = preferences.general
		field = getPreferencesField(Preferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		def filed = createField(injector, preferences, field, parentValue)
		assertThat preferences.general.fields, is(10)
	}
}
