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

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

abstract class AbstractCustomActionsTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Custom Actions Preferences Dialog Test"

	static final String CUSTOM_OK = "Custom Ok"

	static final String CUSTOM_CANCEL = "Custom Cancel"

	static final String CUSTOM_APPLY = "Custom Apply"

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

	static class OkAction extends AbstractAction {

		OkAction() {
			super(CUSTOM_OK)
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	static class CancelAction extends AbstractAction {

		CancelAction() {
			super(CUSTOM_CANCEL)
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	static class ApplyAction extends AbstractAction {

		ApplyAction() {
			super(CUSTOM_APPLY)
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	String name = "test"

	Preferences preferences

	@Before
	void beforeTest() {
		endDelay = 0
		preferences = new Preferences()
	}

	@Override
	def createFrame(def title, def component) {
		def frame = super.createFrame(title, component)
		preferenceDialog.okAction = new OkAction()
		preferenceDialog.cancelAction = new CancelAction()
		preferenceDialog.applyAction = new ApplyAction()
		return frame
	}

	@Test
	void testClickEnterTextOk() {
		beginPanelFrame TITLE, preferences, {
			dialog.title = TITLE
			preferenceDialog.name = name
			fixture.button("$name-$OK_BUTTON_NAME_POSTFIX").requireText CUSTOM_OK
			fixture.button("$name-$CANCEL_BUTTON_NAME_POSTFIX").requireText CUSTOM_CANCEL
			fixture.button("$name-$APPLY_BUTTON_NAME_POSTFIX").requireText CUSTOM_APPLY
			fixture.textBox("name").deleteText()
			fixture.textBox("name").enterText "name"
		},{
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
		},{
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
		},{
			fixture.button("$name-$APPLY_BUTTON_NAME_POSTFIX").click()
			frame.visible = false
			assert preferences.general.name == "name"
		}
	}
}
