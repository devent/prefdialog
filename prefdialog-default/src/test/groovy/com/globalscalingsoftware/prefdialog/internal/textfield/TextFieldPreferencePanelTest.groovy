package com.globalscalingsoftware.prefdialog.internal.textfield
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class TextFieldPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@TextField
		String name = ""
		
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
	
	def fieldsFactory
	
	def reflectionToolbox
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		parentValue = preferences.general
		field = getPreferencesField(Preferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
		fieldsFactory = injector.getInstance(IFieldsFactory)
		reflectionToolbox = injector.getInstance(IReflectionToolbox)
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		def factory = getInjector().getInstance(IFieldsFactory)
		def field = factory.createField(preferences, field, parentValue)
		
		field.setReflectionToolbox(reflectionToolbox)
		field.setFieldsFactory(fieldsFactory)
		field.setup()
		
		createDialog({ field.getComponent() })
		assertThat preferences.general.name, is("name")
	}
}
