package com.globalscalingsoftware.prefdialog.internal.radiobutton

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class RadioButtonTest extends AbstractPreferencePanelTest {
	
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
		window.panel("general").button("apply").click()
		
		assert window.label("label-colors").text() == "colors: "
		assert preferences.general.colors == Colors.BLUE
	}
}
