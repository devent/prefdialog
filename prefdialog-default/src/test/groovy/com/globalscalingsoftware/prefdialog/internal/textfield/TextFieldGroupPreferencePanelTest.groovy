package com.globalscalingsoftware.prefdialog.internal.textfield



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class TextFieldGroupPreferencePanelTest extends AbstractPreferenceTest {
	
	static class Group {
		
		@TextField
		String textField1 = ""
		
		@TextField
		String textField2 = ""
	}
	
	static class General {
		
		@TextField
		String preGroup = ""
		
		@com.globalscalingsoftware.prefdialog.annotations.Group
		Group group1 = new Group()
		
		@com.globalscalingsoftware.prefdialog.annotations.Group
		Group group2 = new Group()
		
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
		assertThat preferences.general.group.textField1, is("name")
	}
}