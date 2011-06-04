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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button

import javax.swing.Action
import javax.swing.JFrame

import org.junit.Test

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler
import com.globalscalingsoftware.prefdialog.annotations.ButtonGroup
import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest

class ManualButtonGroupTest extends AbstractPreferencePanelTest {

	static class General {

		@ButtonGroup
		List<Action> buttons = [
			new Button1Action(),
			new Button2Action()
		]

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
		preferences.general.buttons[1].callback = {
			panelHandler.applyInput()
			frame.visible = false
		}
		panelName = "General"
	}

	@Test
	void testClickApplyAndClose() {
		fixture.button("button-1-buttons").click()
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
