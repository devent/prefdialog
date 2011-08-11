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

class FormattedTextFieldTitleTest extends AbstractPreferencePanelTest {

	static class General {

		@FormattedTextField
		double fields1 = 4

		@FormattedTextField(title='Number of fields')
		double fields2 = 4

		@FormattedTextField(showTitle=false)
		double fields3 = 4

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
		assert fixture.textBox('fields1').text() == '4'
		assert fixture.label('label-fields1').text() == 'fields1'
		assert fixture.textBox('fields2').text() == '4'
		assert fixture.label('label-fields2').text() == 'Number of fields'
		assert fixture.textBox('fields3').text() == '4'
	}
}
