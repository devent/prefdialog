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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield


import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractPreferencePanelTest

class FormattedTextFieldDoubleTest extends AbstractPreferencePanelTest {

	static class General {

		@FormattedTextField
		double decimal = 0d

		@FormattedTextField
		double decimal_second = 0d

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
	void testEnterValidAndApply() {
		fixture.textBox('decimal').deleteText()
		fixture.textBox('decimal').enterText '0.001'
		panelHandler.applyInput()

		assert fixture.textBox('decimal').text() == '0.001'
		assert preferences.general.decimal == 0.001
	}

	@Test
	void testManually() {
		//Thread.sleep 60000
	}
}
