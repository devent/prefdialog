package com.globalscalingsoftware.prefdialog.internal.radiobutton



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class MultipleRadioButtonPreferencePanelTest extends AbstractPreferenceTest {
	
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
		assertThat preferences.general.colors1, is(Colors.BLUE)
	}
}
