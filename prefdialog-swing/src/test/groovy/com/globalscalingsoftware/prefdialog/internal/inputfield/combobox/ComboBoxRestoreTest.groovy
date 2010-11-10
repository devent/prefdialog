package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox

import java.util.List;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class ComboBoxRestoreTest extends AbstractPreferencePanelTest {
	
	static class General {
		
		@ComboBoxElements("Some combo box")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]
		
		@ComboBox("Some combo box")
		String comboBox = "first element"
		
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
		window.comboBox("comboBox").selectItem 1
		window.panel("general").button("restore").click()
		
		window.comboBox("comboBox").requireSelection 0
		assert preferences.general.comboBox == "first element"
	}
}
