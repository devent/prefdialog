/*
 * Copyright 2010-2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-dialog.
 * 
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.PreferenceDialog.*

import java.util.List

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Checkbox
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.FormattedTextField
import com.anrisoftware.prefdialog.annotations.RadioButton
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.validators.NotEmptyString

abstract class AbstractWidthTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Preferences Dialog Widths Test"

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

	String name = "test"

	Preferences preferences

	@Before
	void beforeTest() {
		endDelay = 0
		preferences = new Preferences()
	}

	@Test
	void testClickOkAndClose() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").enterText "name"
			fixture.textBox("fields").enterText "10"
			fixture.checkBox("automaticSave").click()
			fixture.radioButton("colors-BLUE").click()
			fixture.comboBox("comboBox").selectItem 1
			fixture.button("$name-$OK_BUTTON_NAME_POSTFIX").click()
			frame.visible = false
			assert preferences.general.name == "name"
			assert preferences.general.fields == 104
			assert preferences.general.automaticSave == true
			assert preferences.general.colors == Colors.BLUE
			assert preferences.general.comboBox == "second element"
		}
	}
}
