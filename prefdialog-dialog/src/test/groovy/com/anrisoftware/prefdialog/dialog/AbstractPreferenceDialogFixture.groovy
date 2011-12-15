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
package com.anrisoftware.prefdialog.dialog



import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.DialogFixture
import org.junit.After
import org.junit.Before

import com.anrisoftware.prefdialog.PreferenceDialogHandlerFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

abstract class AbstractPreferenceDialogFixture {

	def preferences

	DialogFixture fixture

	def dialogHandler

	@Before
	void beforeTest() {
		setupPreferences()
		fixture = createFrameFixture(preferences)
		fixture.show();
	}

	abstract setupPreferences()

	def createFrameFixture(def preferences) {
		def injector = Guice.createInjector(new PrefdialogModule(), new PrefdialogCoreFieldsModule())
		def factory = injector.getInstance(PreferenceDialogHandlerFactory)
		dialogHandler = createDialogHandler(factory)

		def dialog = GuiActionRunner.execute([executeInEDT: { return dialogHandler.AWTComponent } ] as GuiQuery);
		return new DialogFixture(dialog);
	}

	def createDialogHandler(def factory) {
		return factory.create(null, preferences).createDialog()
	}

	@After
	void afterTest() {
		fixture.cleanUp()
	}
}
