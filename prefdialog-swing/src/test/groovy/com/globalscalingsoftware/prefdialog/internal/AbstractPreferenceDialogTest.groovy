/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
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
		
		//createDialog(controller)
		def dialog = GuiActionRunner.execute([executeInEDT: { return createDialog(controller) } ] as GuiQuery);
		return new DialogFixture(dialog);
	}
	
	protected createDialog(def controller) {
		controller.setup(null)
		return controller.getPreferenceDialog()
	}
	
	@After
	void afterTest() {
		window.cleanUp()
	}
}
