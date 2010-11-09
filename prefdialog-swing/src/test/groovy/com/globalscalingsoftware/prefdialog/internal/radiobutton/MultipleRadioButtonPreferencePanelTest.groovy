package com.globalscalingsoftware.prefdialog.internal.radiobutton



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class MultipleRadioButtonPreferencePanelTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@RadioButton(columns=2)
		Colors colors1 = Colors.BLACK
		
		@RadioButton(columns=2)
		Colors colors2 = Colors.BLUE
		
		@RadioButton
		Colors colors3 = Colors.CYAN
		
		@RadioButton
		Colors colors4 = Colors.GREEN
		
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
		window.radioButton("colors1-BLUE").click()
		window.radioButton("colors2-BLUE").click()
		window.radioButton("colors3-BLUE").click()
		window.radioButton("colors4-BLUE").click()
		window.panel("general").button("apply").click()
		
		window.label("label-colors1").requireText "colors1: "
		window.label("label-colors2").requireText "colors2: "
		window.label("label-colors3").requireText "colors3: "
		window.label("label-colors4").requireText "colors4: "
		assert preferences.general.colors1 == Colors.BLUE
		assert preferences.general.colors2 == Colors.BLUE
		assert preferences.general.colors3 == Colors.BLUE
		assert preferences.general.colors4 == Colors.BLUE
	}
}
