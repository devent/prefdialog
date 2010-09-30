package com.globalscalingsoftware.prefdialog.internal.inputfield




import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PreferenceDialogCancelTest extends AbstractPreferenceTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@TextField
		String name = ""
		
		@Override
		public String toString() {
			"General"
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
		def controller = injector.getInstance(IPreferenceDialogController)
		controller.openDialog()
		
		def option = controller.getOption()
		assertThat option, is(Options.CANCEL)
		assertThat preferences.general.name, is("")
	}
}
