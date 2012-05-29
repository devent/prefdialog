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

import static com.anrisoftware.prefdialog.PreferenceDialog.*


import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Checkbox
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.FormattedTextField
import com.anrisoftware.prefdialog.annotations.Group
import com.anrisoftware.prefdialog.annotations.RadioButton
import com.anrisoftware.prefdialog.annotations.TextField
import com.anrisoftware.prefdialog.validators.NotEmptyString

abstract class AbstractGroupsTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Group Preferences Dialog Test"

	static class Preferences {

		@Child
		General general = new General()
	}

	static class General {

		@TextField(validator=NotEmptyString, validatorText="Must not be empty")
		String name = ""

		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 900")
		int fields = 4

		@Group
		Group1 group1 = new Group1()

		@Group
		Group2 group2 = new Group2()

		@Checkbox
		boolean automaticSave = false

		@RadioButton(columns=2)
		Colors colors = Colors.BLACK

		List combobox1 = [
			"first element",
			"second element",
			"third element"
		]

		@ComboBox(title="combobox1", elements="combobox1")
		String comboBox = "first element"

		@Override
		public String toString() {
			"General"
		}
	}

	static class Group1 {

		@TextField
		String textField1 = ""

		@TextField
		String textField2 = ""
	}

	static class Group2 {

		@TextField
		String textField3 = ""

		@TextField
		String textField4 = ""
	}

	String name = "test"

	Preferences preferences

	@Before
	void beforeTest() {
		endDelay = 0
		preferences = new Preferences()
	}

	@Test
	void testClickOk() {
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

	@Test
	void testClickCancel() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").enterText "name"
			fixture.textBox("fields").enterText "10"
			fixture.checkBox("automaticSave").click()
			fixture.radioButton("colors-BLUE").click()
			fixture.comboBox("comboBox").selectItem 1
			fixture.button("$name-$CANCEL_BUTTON_NAME_POSTFIX").click()
			frame.visible = false

			assert preferences.general.name == ""
			assert preferences.general.fields == 4
			assert preferences.general.automaticSave == false
			assert preferences.general.colors == Colors.BLACK
			assert preferences.general.comboBox == "first element"
		}
	}

	@Test
	void testClickApply() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").enterText "name"
			fixture.textBox("fields").enterText "10"
			fixture.checkBox("automaticSave").click()
			fixture.radioButton("colors-BLUE").click()
			fixture.comboBox("comboBox").selectItem 1
			fixture.button("$name-$APPLY_BUTTON_NAME_POSTFIX").click()
			frame.visible = false

			assert preferences.general.name == "name"
			assert preferences.general.fields == 104
			assert preferences.general.automaticSave == true
			assert preferences.general.colors == Colors.BLUE
			assert preferences.general.comboBox == "second element"
		}
	}
}
