package com.globalscalingsoftware.prefdialog.internal


import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;

abstract class AbstractPreferenceDialogTest {
	
	protected preferences
	
	protected DialogFixture window
	
	@Before
	void beforeTest() {
		setupPreferences()
		window = createFrameFixture(preferences)
		window.show();
	}
	
	abstract setupPreferences()
	
	private createFrameFixture(def preferences) {
		def injector = new PreferencesDialogInjectorFactory().create(preferences)
		def controller = injector.getInstance(PreferenceDialogController)
		
		def dialog = GuiActionRunner.execute([executeInEDT: {
				controller.setup(null)
				return controller.getPreferenceDialog()
			} ] as GuiQuery);
		return new DialogFixture(dialog);
	}
	
	@After
	void afterTest() {
		window.cleanUp()
	}
}
