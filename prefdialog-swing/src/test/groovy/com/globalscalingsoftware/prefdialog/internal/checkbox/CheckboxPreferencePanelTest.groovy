package com.globalscalingsoftware.prefdialog.internal.checkbox



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 

class CheckboxPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@Checkbox
		boolean automaticSave = false
		
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
		window.checkBox("automaticSave").click()
		window.panel("general").button("apply").click()
	}
}
