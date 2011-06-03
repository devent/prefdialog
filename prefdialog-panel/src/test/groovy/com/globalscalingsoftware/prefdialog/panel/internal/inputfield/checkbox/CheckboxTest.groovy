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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Checkbox
import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest

class CheckboxTest extends AbstractPreferencePanelTest {

	static class General {

		@Checkbox
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

	def setupPreferences() {
		preferences = new Preferences()
		panelName = "General"
	}

	@Test
	void testPanelClickApply() {
		fixture.checkBox("automaticSave").click()
		panelHandler.applyInput()

		assert preferences.general.automaticSave == true
	}

	@Test
	void testPanelClickRestore() {
		fixture.checkBox("automaticSave").click()
		panelHandler.restoreInput()

		assert preferences.general.automaticSave == false
	}
}
