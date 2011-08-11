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
package com.globalscalingsoftware.prefdialog.panel.inputfields.combobox

import java.util.List

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.ComboBox
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractPreferencePanelTest

class ComboBoxReadOnlyTest extends AbstractPreferencePanelTest {

	static class General {

		@ComboBoxElements('Some combo box')
		List<String> comboBoxElements = [
			'first element',
			'second element',
			'third element'
		]

		@ComboBox(elements='Some combo box', readonly=true)
		String comboBox = 'first element'

		@Override
		public String toString() {
			'General'
		}
	}

	static class Preferences {

		@Child
		General general = new General()
	}

	def setupPreferences() {
		preferences = new Preferences()
		panelName = 'General'
	}

	@Test
	void testPanelClickApplyAndClose() {
		fixture.comboBox('comboBox').requireDisabled()
		fixture.comboBox('comboBox').requireSelection 0
	}
}