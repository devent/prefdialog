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


import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

abstract class AbstractDialogTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Preferences Dialog Test"

	static class Preferences {

		@Child
		General general = new General()

		@Override
		String toString() {
			"Preferences"
		}
	}

	static class General {

		@TextField
		String name = ""

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
	void "components titles"() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			assert fixture.textBox("name").text() == ""
			assert fixture.button("$name-$OK_BUTTON_NAME_POSTFIX").text() == "Ok"
			assert fixture.button("$name-$CANCEL_BUTTON_NAME_POSTFIX").text() == "Cancel"
			assert fixture.button("$name-$APPLY_BUTTON_NAME_POSTFIX").text() == "Apply"
		}
	}

	@Test
	void testClickEnterTextOk() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").deleteText()
			fixture.textBox("name").enterText "name"
			fixture.button("$name-$OK_BUTTON_NAME_POSTFIX").click()
			frame.visible = false
			assert preferences.general.name == "name"
		}
	}

	@Test
	void testClickEnterTextCancel() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").enterText "name"
			fixture.button("$name-$CANCEL_BUTTON_NAME_POSTFIX").click()
			frame.visible = false
			assert preferences.general.name == ""
		}
	}

	@Test
	void testClickEnterTextApply() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.textBox("name").enterText "name"
			fixture.button("$name-$APPLY_BUTTON_NAME_POSTFIX").click()
			frame.visible = false
			assert preferences.general.name == "name"
		}
	}
}
