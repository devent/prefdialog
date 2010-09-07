package com.globalscalingsoftware.prefdialog.internal.formattedtextfield


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferencePanelTest 

class PreferencePanelTest extends AbstractPreferencePanelTest {
	
	def preferences
	
	def parentValue
	
	def field
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		parentValue = preferences.general
		field = getPreferencesField(preferences, "general")
	}
	
	@Test
	void testClickApplyAndClose() {
		def factory = getInjector().getInstance(IPreferencePanelFactory)
		def controller = factory.create(parentValue, field)
		
		createDialog({ controller.getPanel() })
		assertThat preferences.general.fields, is(10)
	}
}
