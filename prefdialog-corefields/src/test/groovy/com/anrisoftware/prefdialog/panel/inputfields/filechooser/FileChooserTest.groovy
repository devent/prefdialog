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
package com.anrisoftware.prefdialog.panel.inputfields.filechooser

import java.awt.event.KeyEvent

import org.fest.swing.core.KeyPressInfo
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FileChooser
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserTest extends AbstractFieldFixture {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static class General {

		@FileChooser
		File file = new File("")
	}

	FileChooserTest() {
		super(new General(), 'file', factory)
	}

	@Test
	void "titels"() {
		fixture.label('label-file').requireVisible()
		assert fixture.label('label-file').text() == 'file'
		fixture.textBox('filetextfield-file').requireVisible()
		assert fixture.textBox('filetextfield-file').text() == new File("").absolutePath
		fixture.button('openfilebutton-file').requireVisible()
	}

	@Test
	void "select a file from the dialog and apply input"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.button('openfilebutton-file').click()
		fixture.fileChooser('filechooser-file').setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser('filechooser-file').selectFile tmpfile
		fixture.fileChooser('filechooser-file').approve()
		inputField.applyInput parentObject

		assert parentObject.file == tmpfile
	}

	@Test
	void "select a file from the dialog and restore input"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.button('openfilebutton-file').click()
		fixture.fileChooser('filechooser-file').setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser('filechooser-file').selectFile tmpfile
		fixture.fileChooser('filechooser-file').approve()
		inputField.restoreInput parentObject

		assert parentObject.file == new File('')
	}

	@Test
	void "enter file name and apply input"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.textBox('filetextfield-file').deleteText()
		fixture.textBox('filetextfield-file').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-file').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		inputField.applyInput parentObject

		assert parentObject.file == tmpfile
	}

	@Test
	void "enter file name and restore input"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();

		fixture.textBox('filetextfield-file').deleteText()
		fixture.textBox('filetextfield-file').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-file').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		inputField.restoreInput parentObject

		assert parentObject.file == new File('')
	}

	@Test
	void "manually"() {
		Thread.sleep 60000
	}
}
