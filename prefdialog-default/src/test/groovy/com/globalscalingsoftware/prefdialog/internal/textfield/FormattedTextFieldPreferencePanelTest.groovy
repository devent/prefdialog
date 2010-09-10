package com.globalscalingsoftware.prefdialog.internal.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class FormattedTextFieldPreferencePanelTest extends AbstractPreferenceTest {
	
	def preferences
	
	def parentValue
	
	def field
	
	def injector
	
	@Before
	void beforeTest() {
		preferences = new FormattedTextFieldPreferences()
		parentValue = preferences.general
		field = getPreferencesField(FormattedTextFieldPreferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		def factory = getInjector().getInstance(IPreferencePanelFactory)
		def controller = factory.create(parentValue, field)
		controller.setupPanel()
		
		createDialog({ controller.getPanel() })
		assertThat preferences.general.fields, is(10)
	}
}
