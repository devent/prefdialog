package com.globalscalingsoftware.prefdialog.internal.radiobutton

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import org.junit.Test;

class RadioButtonPreferencePanelWithColumnsTest extends AbstractPreferenceTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@RadioButton(columns=2, width=-2.0d)
		Colors colors = Colors.BLACK
		
		@Override
		public String toString() {
			"General"
		}
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
		
		window.label("label-colors").requireText "colors: "
		assert preferences.general.colors == Colors.BLUE
	}
}
