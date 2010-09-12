package com.globalscalingsoftware.prefdialog.internal.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.HelpText 
import com.globalscalingsoftware.prefdialog.annotations.Validated 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.FieldsValidator 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class FormattedTextFieldPreferencePanelTest extends AbstractPreferenceTest {
	
	class General {
		
		@FormattedTextField(width=-2.0d)
		@HelpText("Must be a number and between 2 and 100")
		@Validated(FieldsValidator)
		int fields = 4
		
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
		assertThat preferences.general.fields, is(10)
	}
}
