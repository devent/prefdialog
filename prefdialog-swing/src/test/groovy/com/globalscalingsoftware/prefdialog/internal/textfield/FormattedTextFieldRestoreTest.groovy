package com.globalscalingsoftware.prefdialog.internal.textfield
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class FormattedTextFieldRestoreTest extends AbstractPreferencePanelTest {
	
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
		preferencesClass = Preferences
		preferences = new Preferences()
		preferencesParentName = "general"
		preferencesParentValue = preferences.general
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		window.textBox("fields").enterText "10"
		window.panel("general").button("restore").click()
		
		assert window.textBox("fields").text() == "4"
		assert preferences.general.fields == 4
	}
}
