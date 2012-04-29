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

import java.awt.Dimension
import java.util.List

import org.junit.Before;
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Checkbox
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.FormattedTextField
import com.anrisoftware.prefdialog.annotations.RadioButton
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.validators.NotEmptyString

class DialogWidthTest extends AbstractPreferenceDialogFixture {

	static class Preferences {

		@Child
		General general = new General()
	}

	static class General {

		@TextField(width=-2.0d, validator=NotEmptyString, validatorText="Must not be empty")
		String name = ""

		@FormattedTextField(width=-2.0d, validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4

		@Checkbox(width=-2.0d)
		boolean automaticSave = false

		@RadioButton(width=-2.0d, columns=2)
		Colors colors = Colors.BLACK

		List combobox1 = [
			"first element",
			"second element",
			"third element"
		]

		@ComboBox(title="combobox1", elements="combobox1", width=-2.0d)
		String comboBox = "first element"

		@Override
		public String toString() {
			"General"
		}
	}

	def preferences

	@Before
	void beforeTest() {
		frameSize = new Dimension(640, 480)
		preferences = new Preferences()
	}

	@Test
	void testClickOkAndClose() {
		doDialogTest "Width Preferences Dialog Test", preferences, {
			fixture.textBox("name").enterText "name"
			fixture.textBox("fields").enterText "10"
			fixture.checkBox("automaticSave").click()
			fixture.radioButton("colors-BLUE").click()
			fixture.comboBox("comboBox").selectItem 1
			fixture.button("ok").click()

			assert preferences.general.name == "name"
			assert preferences.general.fields == 104
			assert preferences.general.automaticSave == true
			assert preferences.general.colors == Colors.BLUE
			assert preferences.general.comboBox == "second element"
		}
	}
}
