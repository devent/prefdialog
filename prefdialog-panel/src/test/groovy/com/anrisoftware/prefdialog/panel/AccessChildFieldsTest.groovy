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
package com.anrisoftware.prefdialog.panel

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.HorizontalPositions
import com.anrisoftware.prefdialog.annotations.RadioButton
import com.anrisoftware.prefdialog.annotations.TextField

class AccessChildFieldsTest extends AbstractPanelFixture {

	static class Button1Action extends AbstractAction {

		Button1Action() {
			super("Button 1")
		}

		void actionPerformed(ActionEvent action) {
		}
	}

	static class Button2Action extends AbstractAction {

		Button2Action() {
			super("Button 2")
		}

		void actionPerformed(ActionEvent action) {
		}
	}

	static class General {

		@TextField
		String text = ""

		@RadioButton(columns=2)
		Colors colors = Colors.BLACK

		@ButtonGroup
		def buttons = [
			new Button1Action(),
			new Button2Action()
		]
	}

	static class Preferences {

		@Child
		General general = new General()
	}

	AccessChildFieldsTest() {
		super(new Preferences(), "general")
	}

	@Test
	void "find fields"() {
		panel.getField("general").setComponentTitle "New Title"
		panel.getField("text").setComponentTitle "Text Field:"
		panel.getField("buttons").setHorizontalPosition HorizontalPositions.MIDDLE
		Thread.sleep 10000
	}

	@Test
	void "find button in buttons group"() {
		assert panel.getField("buttons").getButton(0).text == "Button 1"
		assert panel.getField("buttons").getButton(1).text == "Button 2"
	}
}
