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
package com.globalscalingsoftware.prefdialog.panel.inputfields.filechooser

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.FileChooser
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractPreferencePanelTest

class FileChooserTitleTest extends AbstractPreferencePanelTest {

	static class General {

		@FileChooser
		File file1 = new File('')

		@FileChooser(title='Save to file:')
		File file2 = new File('')

		@FileChooser(showTitle=false)
		File file3 = new File('')

		@Override
		public String toString() {
			'General'
		}
	}

	static class Preferences {

		@Child
		General general = new General()
	}

	def setupPreferences() {
		preferences = new Preferences()
		panelName = 'General'
	}

	@Test
	void testInputs() {
		assert fixture.label('label-file1').text() == 'file1'
		assert fixture.label('label-file2').text() == 'Save to file:'
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}