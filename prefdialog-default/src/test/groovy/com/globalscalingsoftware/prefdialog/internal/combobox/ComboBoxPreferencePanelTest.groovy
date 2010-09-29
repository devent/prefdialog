package com.globalscalingsoftware.prefdialog.internal.combobox

import java.util.List;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class ComboBoxPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@ComboBoxElements("combobox1")
		List<String> comboBoxElements = ["first element", "second element", "third element"]
		
		@ComboBox("combobox1")
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
	
	def preferences
	
	def parentValue
	
	def field
	
	def injector
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		parentValue = preferences.general
		field = getPreferencesField(Preferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		def filed = createField(injector, preferences, field, parentValue)
		assertThat preferences.general.comboBox, is("second element")
	}
}
