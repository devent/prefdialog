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

import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import java.awt.event.KeyEvent

import org.fest.swing.core.KeyPressInfo
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.FileChooser
import com.anrisoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserTest extends AbstractFieldFixture {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static final String FILE = "file"

	static class General {

		@FileChooser
		File file = new File("")
	}

	FileChooserTest() {
		super(new General(), FILE, factory)
	}

	@Test
	void "titels"() {
		fixture.label("$TITLE_LABEL-$FILE").requireVisible()
		assert fixture.label("$TITLE_LABEL-$FILE").text() == FILE
		fixture.textBox("filetextfield-$FILE").requireVisible()
		assert fixture.textBox("filetextfield-$FILE").text() == new File("").absolutePath
		fixture.button("openfilebutton-$FILE").requireVisible()
	}

	@Test
	void "select a file from the dialog and apply input"() {
		File tmpfile = File.createTempFile("fileChooserTest", null)
		tmpfile.deleteOnExit();

		fixture.button("openfilebutton-$FILE").click()
		fixture.fileChooser("filechooser-$FILE").setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser("filechooser-$FILE").selectFile tmpfile
		fixture.fileChooser("filechooser-$FILE").approve()
		inputField.applyInput parentObject

		assert parentObject.file == tmpfile
	}

	@Test
	void "select a file from the dialog and restore input"() {
		File tmpfile = File.createTempFile("fileChooserTest", null)
		tmpfile.deleteOnExit();

		fixture.button("openfilebutton-$FILE").click()
		fixture.fileChooser("filechooser-$FILE").setCurrentDirectory(tmpfile.getParentFile())
		fixture.fileChooser("filechooser-$FILE").selectFile tmpfile
		fixture.fileChooser("filechooser-$FILE").approve()
		inputField.restoreInput parentObject

		assert parentObject.file == new File("")
	}

	@Test
	void "enter file name and apply input"() {
		File tmpfile = File.createTempFile("fileChooserTest", null)
		tmpfile.deleteOnExit();

		fixture.textBox("filetextfield-$FILE").deleteText()
		fixture.textBox("filetextfield-$FILE").enterText tmpfile.absolutePath
		fixture.textBox("filetextfield-$FILE").pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		inputField.applyInput parentObject

		assert parentObject.file == tmpfile
	}

	@Test
	void "enter file name and restore input"() {
		File tmpfile = File.createTempFile("fileChooserTest", null)
		tmpfile.deleteOnExit();

		fixture.textBox("filetextfield-$FILE").deleteText()
		fixture.textBox("filetextfield-$FILE").enterText tmpfile.absolutePath
		fixture.textBox("filetextfield-$FILE").pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_ENTER)
		inputField.restoreInput parentObject

		assert parentObject.file == new File("")
	}

	@Test
	void "manually"() {
		// Thread.sleep 60000
	}
}
