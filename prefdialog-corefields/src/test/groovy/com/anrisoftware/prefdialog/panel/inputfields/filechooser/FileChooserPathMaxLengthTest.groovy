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
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory

class FileChooserPathMaxLengthTest extends FieldFixtureHandler {

	static factory = injector.getInstance(FileChooserFieldHandlerFactory)

	static class General {

		@FileChooser
		File defaultPathMaxLength = new File('')

		@FileChooser(pathMaxLength=120)
		File longPathMaxLength = new File('')

		@FileChooser(pathMaxLength=10)
		File shortPathMaxLength = new File('')
	}

	@Test
	void "default path max length"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();
		createFieldFixture(new General(), 'defaultPathMaxLength', factory)
		beginFixture()
		fixture.textBox('filetextfield-defaultPathMaxLength').deleteText()
		fixture.textBox('filetextfield-defaultPathMaxLength').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-defaultPathMaxLength').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_TAB)
		inputField.applyInput parentObject
		assert parentObject.defaultPathMaxLength == tmpfile
		endFixture()
	}

	@Test
	void "long path max length"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();
		createFieldFixture(new General(), 'longPathMaxLength', factory)
		beginFixture()
		fixture.textBox('filetextfield-longPathMaxLength').deleteText()
		fixture.textBox('filetextfield-longPathMaxLength').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-longPathMaxLength').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_TAB)
		inputField.applyInput parentObject
		assert parentObject.longPathMaxLength == tmpfile
		endFixture()
	}

	@Test
	void "short path max length"() {
		File tmpfile = File.createTempFile('fileChooserTest', null)
		tmpfile.deleteOnExit();
		createFieldFixture(new General(), 'shortPathMaxLength', factory)
		beginFixture()
		fixture.textBox('filetextfield-shortPathMaxLength').deleteText()
		fixture.textBox('filetextfield-shortPathMaxLength').enterText tmpfile.absolutePath
		fixture.textBox('filetextfield-shortPathMaxLength').pressAndReleaseKey KeyPressInfo.keyCode(KeyEvent.VK_TAB)
		inputField.applyInput parentObject
		assert parentObject.shortPathMaxLength == tmpfile
		endFixture()
	}

	@Test
	void testManual() {
		//Thread.sleep(30000)
	}
}
