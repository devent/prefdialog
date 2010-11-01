package com.globalscalingsoftware.prefdialog.internal.inputfield




import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PreferenceDialogWithMoreChildrenTest extends AbstractPreferenceTest {
	
	static class Preferences {
		
		@Child
		Child1 general = new Child1()
		
		@Child
		Child2 child2 = new Child2()
	}
	
	static class Child1 {
		
		@TextField
		String name = ""
		
		@FormattedTextField
		int fields = 4
		
		@Override
		public String toString() {
			"Child1"
		}
	}
	
	static class Child2 {
		
		@TextField
		String something = ""
		
		@FormattedTextField
		int moreFields = 4
		
		@Override
		public String toString() {
			"Child2"
		}
	}
	
	def injector
	
	def preferences
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testDialogClickOk() {
		def controller = injector.getInstance(PreferenceDialogController)
		controller.openDialog()
	}
}
