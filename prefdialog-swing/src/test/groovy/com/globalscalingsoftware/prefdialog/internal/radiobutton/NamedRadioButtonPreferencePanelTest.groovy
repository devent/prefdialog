package com.globalscalingsoftware.prefdialog.internal.radiobutton



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 

class NamedRadioButtonPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@RadioButton("Some colors")
		Colors colors = Colors.BLACK
		
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
		window.radioButton("colors-BLUE").click()
		window.panel("general").button("apply").click()
		
		window.label("label-colors").requireText "Some colors: "
		assert preferences.general.colors == Colors.BLUE
	}
}
