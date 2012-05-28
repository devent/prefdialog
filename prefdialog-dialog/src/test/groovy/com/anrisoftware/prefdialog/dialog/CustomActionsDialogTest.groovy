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
import java.awt.event.ActionEvent

import javax.swing.AbstractAction

import org.junit.Before;
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.TextField

class CustomActionsDialogTest extends TestPreferenceDialogUtil {

	static final String TITLE = "Custom Actions Preferences Dialog Test"

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
			super("Custom Ok")
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	static class CancelAction extends AbstractAction {

		CancelAction() {
			super("Custom Cancel")
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	static class ApplyAction extends AbstractAction {

		ApplyAction() {
			super("Custom Apply")
		}

		void actionPerformed(ActionEvent e) {
		}
	}

	def preferences

	@Before
	void beforeTest() {
		frameSize = new Dimension(640, 480)
		preferences = new Preferences()
	}

	def createDialogHandler(def preferences) {
		def handler = super.createDialogHandler(preferences)
		handler.okAction = new OkAction()
		handler.cancelAction = new CancelAction()
		handler.applyAction = new ApplyAction()
		return handler
	}

	@Test
	void testClickEnterTextOk() {
		doDialogTest TITLE, preferences, {
			fixture.textBox("name").deleteText()
			fixture.textBox("name").enterText "name"
			fixture.button("ok").click()
			assert preferences.general.name == "name"
		}
	}

	@Test
	void testClickEnterTextCancel() {
		doDialogTest TITLE, preferences, {
			fixture.textBox("name").enterText "name"
			fixture.button("cancel").click()
			assert preferences.general.name == ""
		}
	}

	@Test
	void testClickEnterTextApply() {
		doDialogTest TITLE, preferences, {
			fixture.textBox("name").enterText "name"
			fixture.button("apply").click()
			assert fixture.textBox("name").text() == "name"
			assert preferences.general.name == "name"
		}
	}

	@Test
	void testManual() {
		doDialogTest TITLE, preferences, {
			//
			Thread.sleep 0 // 60000 //
		}
	}
}
