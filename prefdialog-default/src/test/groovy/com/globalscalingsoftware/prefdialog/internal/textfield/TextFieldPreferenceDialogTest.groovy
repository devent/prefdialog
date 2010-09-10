package com.globalscalingsoftware.prefdialog.internal.textfield


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class TextFieldPreferenceDialogTest extends AbstractPreferenceTest {
	
	def injector
	
	def preferences
	
	@Before
	void beforeTest() {
		preferences = new TextFieldPreferences()
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testDialogClickOk() {
		def controller = injector.getInstance(IPreferenceDialogController)
		controller.openDialog()
		assertThat preferences.general.name, is("name")
	}
}
