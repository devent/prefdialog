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

import static com.anrisoftware.swingcomponents.fontchooser.api.FontChooserHandler.*
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FieldPanel.*
import java.awt.Dimension
import java.awt.Font

import org.junit.Before;
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FontChooser
import com.anrisoftware.prefdialog.annotations.TextField

class DialogFontChooserTest extends AbstractPreferenceDialogFixture {

	static final String TITLE = "Font Chooser Preferences Dialog Test"

	static class Preferences {

		@Child
		General general = new General()

		@Override
		String toString() {
			'Preferences'
		}
	}

	static class General {

		@TextField
		String name = ''

		@FontChooser
		Font font = Font.decode(null)

		@Override
		public String toString() {
			'General'
		}
	}

	def preferences

	@Before
	void beforeTest() {
		frameSize = new Dimension(640, 480)
		preferences = new Preferences()
	}

	@Test
	void "enter font and apply"() {
		doDialogTest TITLE, preferences, {
			fixture.toggleButton("$OPEN_FONT_BUTTON-font").click()
			fixture.textBox(NAME_FIELD_NAME).deleteText()
			fixture.textBox(NAME_FIELD_NAME).enterText("Serif")
			fixture.textBox(STYLE_FIELD_NAME).deleteText()
			fixture.textBox(STYLE_FIELD_NAME).enterText("Bold")
			fixture.textBox(SIZE_FIELD_NAME).deleteText()
			fixture.textBox(SIZE_FIELD_NAME).enterText("14")
			fixture.button("ok").click()
			assert preferences.general.font == new Font("Serif", Font.BOLD, 14)
		}
	}

	@Test
	void "manually"() {
		doDialogTest TITLE, preferences, {
			//
			Thread.sleep 0 // 60000 //
		}
	}
}