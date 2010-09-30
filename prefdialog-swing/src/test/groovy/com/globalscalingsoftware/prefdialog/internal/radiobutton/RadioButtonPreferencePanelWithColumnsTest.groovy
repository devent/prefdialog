package com.globalscalingsoftware.prefdialog.internal.radiobutton

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import org.junit.Before;
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
		assertThat preferences.general.colors, is(Colors.BLUE)
	}
}
