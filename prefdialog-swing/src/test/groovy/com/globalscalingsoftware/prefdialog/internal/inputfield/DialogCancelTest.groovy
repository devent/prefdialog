package com.globalscalingsoftware.prefdialog.internal.inputfield

import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceDialogTest;

class DialogCancelTest extends AbstractPreferenceDialogTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@TextField
		String name = ""
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	def setupPreferences() {
		preferences = new Preferences()
	}
	
	@Test
	void testClickCancelAndClose() {
		window.textBox("name").enterText "name"
		window.button("cancel").click()
		
		assert preferences.general.name == ""
	}
}
