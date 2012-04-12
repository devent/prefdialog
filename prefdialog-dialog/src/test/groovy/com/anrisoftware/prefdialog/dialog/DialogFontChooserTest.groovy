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


import java.awt.Font

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.annotations.FontChooser
import com.anrisoftware.prefdialog.annotations.TextField

class DialogFontChooserTest extends AbstractPreferenceDialogFixture {

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

	def setupPreferences() {
		preferences = new Preferences()
	}

	@Test
	void "manually"() {
		Thread.sleep(60000)
	}
}
