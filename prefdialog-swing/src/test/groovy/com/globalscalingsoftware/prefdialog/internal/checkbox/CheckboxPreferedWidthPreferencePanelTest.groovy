package com.globalscalingsoftware.prefdialog.internal.checkbox

import javax.swing.JFrame;



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.annotations.fields.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 

class CheckboxPreferedWidthPreferencePanelTest extends AbstractPreferenceTest {
	
	static class General {
		
		@Checkbox(width=-2.0d)
		boolean automaticSave = false
		
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
	
	private FrameFixture window
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		parentValue = preferences.general
		field = getPreferencesField(Preferences, "general")
		injector = new PreferencesDialogInjectorFactory().create(preferences)
		
		JFrame frame = GuiActionRunner.execute(new GuiQuery<JFrame>() {
					protected JFrame executeInEDT() {
						return createDialog(injector, preferences, field, parentValue)
					}
				});
		window = new FrameFixture(frame);
		window.show();
	}
	
	@After
	void afterTest() {
		window.cleanUp()
	}
	
	@Test
	void testPanelClickApplyAndClose() {
		window.checkBox("automaticSave").click()
		window.panel("general").button("apply").click()
		assert preferences.general.automaticSave == true
	}
}
