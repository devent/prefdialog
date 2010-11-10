package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class TextFieldWidthTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@TextField(width=-2.0d)
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
	void testPanelClickApplyAndClose() {
		window.textBox("name").enterText "test"
		window.panel("general").button("apply").click()
		
		assert window.label("label-name").text() == "name: "
		assert preferences.general.name == "test"
	}
}