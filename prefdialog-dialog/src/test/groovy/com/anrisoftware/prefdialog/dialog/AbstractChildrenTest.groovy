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

import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FormattedTextField
import com.anrisoftware.prefdialog.annotations.TextField

abstract class AbstractChildrenTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Children Preferences Dialog Test"

	static class Preferences {

		@Child
		Child1 general = new Child1()

		@Child
		Child2 child2 = new Child2()
	}

	static class Child1 {

		@TextField
		String name = ""

		@FormattedTextField
		int fields = 4

		@Override
		public String toString() {
			"Child1"
		}
	}

	static class Child2 {

		@TextField
		String something = ""

		@FormattedTextField
		int moreFields = 4

		@Override
		public String toString() {
			"Child2"
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
		},{
			def childrenPanel = fixture.panel("$name-${ChildrenPanel.PANEL_NAME_POSTFIX}")
			childrenPanel.tree().clickPath "Child2"
		}, {
			fixture.textBox("something").enterText "text"
			fixture.textBox("moreFields").enterText "20"
		}, {
			fixture.button("$name-$OK_BUTTON_NAME_POSTFIX").click()
			frame.visible = false

			assert preferences.general.name == "name"
			assert preferences.general.fields == 104
			assert preferences.child2.something == "text"
			assert preferences.child2.moreFields == 204
		}
	}
}
