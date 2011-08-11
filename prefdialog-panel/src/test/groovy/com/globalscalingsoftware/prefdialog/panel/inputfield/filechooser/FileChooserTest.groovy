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
package com.globalscalingsoftware.prefdialog.panel.inputfield.filechooser

import java.awt.event.KeyEvent

import org.fest.swing.core.KeyPressInfo
import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Child
import com.globalscalingsoftware.prefdialog.annotations.FileChooser
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractPreferencePanelTest

class FileChooserTest extends AbstractPreferencePanelTest {

	static class General {

		@FileChooser
		File file = new File('')

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
		fixture.label('label-file').requireVisible()
		assert fixture.label('label-file').text() == 'file'
		fixture.textBox('filetextfield-file').requireVisible()
		assert fixture.textBox('filetextfield-file').text() == ''
		fixture.button('openfilebutton-file').requireVisible()
	}

	@Test
	void testSelectFileAndApply() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.panel('general').button('openfilebutton-file').click()
		fixture.fileChooser('filechooser-file').setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser('filechooser-file').selectFile tmpfile
		fixture.fileChooser('filechooser-file').approve()
		panelHandler.applyInput()

		assert preferences.general.file == tmpfile
	}

	@Test
	void testSelectFileAndRestore() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.panel('general').button('openfilebutton-file').click()
		fixture.fileChooser('filechooser-file').setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser('filechooser-file').selectFile tmpfile
		fixture.fileChooser('filechooser-file').approve()
		panelHandler.restoreInput()

		assert preferences.general.file == new File('')
	}

	@Test
	void testEnterFileAndApply() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.textBox('filetextfield-file').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-file').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		panelHandler.applyInput()
		assert preferences.general.file == tmpfile
	}

	@Test
	void testEnterFileAndRestore() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.textBox('filetextfield-file').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-file').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		panelHandler.restoreInput()
		assert preferences.general.file == new File('')
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
