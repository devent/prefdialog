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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.Group
import com.globalscalingsoftware.prefdialog.annotations.TextField
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractPreferencePanelTest

class TextFieldGroupTest extends AbstractPreferencePanelTest {

	static class Group1 {

		@TextField
		String textField1 = ''

		@TextField
		String textField2 = ''
	}

	static class Group2 {

		@TextField
		String textField3 = ''

		@TextField
		String textField4 = ''
	}

	static class General {

		@TextField
		String preGroup = ''

		@Group
		Group1 group1 = new Group1()

		@Group
		Group2 group2 = new Group2()

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
	void testEnterTextAndApply() {
		fixture.textBox('textField1').deleteText()
		fixture.textBox('textField1').enterText 'test1'
		fixture.textBox('textField2').deleteText()
		fixture.textBox('textField2').enterText 'test2'
		fixture.textBox('textField3').deleteText()
		fixture.textBox('textField3').enterText 'test3'
		fixture.textBox('textField4').deleteText()
		fixture.textBox('textField4').enterText 'test4'
		panelHandler.applyInput()

		assert fixture.textBox('textField1').text() == 'test1'
		assert preferences.general.group1.textField1 == 'test1'
		assert fixture.textBox('textField2').text() == 'test2'
		assert preferences.general.group1.textField2 == 'test2'
		assert fixture.textBox('textField3').text() == 'test3'
		assert preferences.general.group2.textField3 == 'test3'
		assert fixture.textBox('textField4').text() == 'test4'
		assert preferences.general.group2.textField4 == 'test4'
	}
}
