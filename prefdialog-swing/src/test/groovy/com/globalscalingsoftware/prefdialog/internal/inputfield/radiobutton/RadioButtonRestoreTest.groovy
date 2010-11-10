package com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 
import com.globalscalingsoftware.prefdialog.internal.inputfield.Colors;

class RadioButtonRestoreTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@RadioButton
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
		window.panel("general").button("restore").click()
		
		window.radioButton("colors-BLACK").requireSelected()
		assert preferences.general.colors == Colors.BLACK
	}
}
