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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.RadioButton
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractPreferencePanelTest
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.Colors

class RadioButtonTest extends AbstractPreferencePanelTest {

	static class General {

		@RadioButton
		Colors colors = Colors.BLACK

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
	void testField() {
		assert fixture.label("label-colors").text() == "colors: "
	}

	@Test
	void testPanelChooseBlueAndApply() {
		fixture.radioButton("colors-BLUE").click()
		panelHandler.applyInput()
		fixture.radioButton("colors-BLUE").requireSelected()

		assert preferences.general.colors == Colors.BLUE
	}

	@Test
	void testPanelChooseBlueAndRestore() {
		fixture.radioButton("colors-BLUE").click()
		panelHandler.restoreInput()
		fixture.radioButton("colors-BLACK").requireSelected()

		assert preferences.general.colors == Colors.BLACK
	}
}
