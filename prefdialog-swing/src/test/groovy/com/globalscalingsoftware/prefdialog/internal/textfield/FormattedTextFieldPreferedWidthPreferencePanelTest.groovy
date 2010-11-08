package com.globalscalingsoftware.prefdialog.internal.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 

class FormattedTextFieldPreferedWidthPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@FormattedTextField(width=-2.0d)
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
	void testPanelClickApplyAndClose() {
		window.textBox("fields").enterText "10"
		window.panel("general").button("apply").click()
		
		assert window.label("label-fields").text() == "fields: "
		assert preferences.general.fields == 10
	}
}
