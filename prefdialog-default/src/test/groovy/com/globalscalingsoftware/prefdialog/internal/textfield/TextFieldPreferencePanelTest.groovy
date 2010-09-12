package com.globalscalingsoftware.prefdialog.internal.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.HelpText 
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class TextFieldPreferencePanelTest extends AbstractPreferenceTest {
	
	class General {
		
		@TextField
		@HelpText("Must not be empty")
		@Validated(NotEmptyStringValidator)
		String name = ""
		
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
		def factory = getInjector().getInstance(IPreferencePanelFactory)
		def controller = factory.create(parentValue, field)
		controller.setupPanel()
		
		createDialog({ controller.getPanel() })
		assertThat preferences.general.name, is("name")
	}
}
