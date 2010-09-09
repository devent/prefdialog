package com.globalscalingsoftware.prefdialog.internal.radiobutton



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class PreferencePanelTest extends AbstractPreferenceTest {
	
	def preferences
	
	def parentValue
	
	def field
	
	def injector
	
	@Before
	void beforeTest() {
		preferences = new RadioButtonPreferences()
		parentValue = preferences.general
		field = getPreferencesField(RadioButtonPreferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		def factory = injector.getInstance(IPreferencePanelFactory)
		def controller = factory.create(parentValue, field)
		controller.setupPanel()
		
		createDialog({ controller.getPanel() })
		assertThat preferences.general.colors, is(Colors.BLUE)
	}
}
