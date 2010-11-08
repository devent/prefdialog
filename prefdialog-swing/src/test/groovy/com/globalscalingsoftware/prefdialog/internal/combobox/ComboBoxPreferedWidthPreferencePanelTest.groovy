package com.globalscalingsoftware.prefdialog.internal.combobox

import java.util.List;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 

class ComboBoxPreferedWidthPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@ComboBoxElements("combobox1")
		List<String> comboBoxElements = [
			"first element",
			"second element",
			"third element"
		]
		
		@ComboBox(value = "combobox1", width = -2.0d)
		String comboBox
		
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
		window.panel("general").button("apply").click()
		
		window.label("label-comboBox").requireText "combobox1: "
		assert preferences.general.comboBox == "second element"
	}
}
