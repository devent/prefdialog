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
package com.anrisoftware.prefdialog.dialog.childrenpanels

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.dialog.PrefdialogModule
import com.anrisoftware.prefdialog.dialog.childrenpanels.DefaultChildrenPanels
import com.anrisoftware.prefdialog.dialog.childrenpanels.DefaultChildrenPanelsFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

class DefaultChildrenPanelsTest extends TestUtils {

	static injector = Guice.createInjector(
	new PrefdialogCoreFieldsModule(),
	new PrefdialogChildrenPanelsModule(),
	new PrefdialogModule())

	static DefaultChildrenPanelsFactory factory = injector.getInstance DefaultChildrenPanelsFactory

	static class Preferences {

		@Child
		General general = new General()

		@Override
		String toString() {
			"Preferences"
		}
	}

	static class General {

		@TextField
		String name = ""

		@Override
		public String toString() {
			"General"
		}
	}

	Preferences preferences

	@Before
	void beforeTest() {
		preferences = new Preferences()
	}

	@Test
	void "discover child panels"() {
		DefaultChildrenPanels panels = factory.create preferences
		assert panels.getSize() == 1
		assert panels.getElementAt(0) == preferences.general
	}
}
