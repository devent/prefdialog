package com.globalscalingsoftware.prefdialog.internal.checkbox



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class CheckboxPreferedWidthPreferencePanelTest extends AbstractPreferenceTest {
	
	class General {
		
		@Checkbox(width=-2.0d)
		boolean automaticSave = false
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	class Preferences {
		
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
		def factory = injector.getInstance(IPreferencePanelFactory)
		def controller = factory.create(parentValue, field)
		controller.setupPanel()
		
		createDialog({ controller.getPanel() })
		assertThat preferences.general.automaticSave, is(true)
	}
}
