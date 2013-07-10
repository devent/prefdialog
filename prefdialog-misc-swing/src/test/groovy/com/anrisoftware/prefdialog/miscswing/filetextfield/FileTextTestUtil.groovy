/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.filetextfield

import javax.swing.JButton
import javax.swing.JPanel

import net.miginfocom.swing.MigLayout

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * Utilities to test the file text field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileTextTestUtil {

	public static final String FILE_FIELD_NAME = "file-field"

	FileTextField field

	JPanel panel

	TestFrameUtil createFrame(def title) {
		field = createFileField()
		panel = createPanel(field)
		new TestFrameUtil(title, panel)
	}

	JPanel createPanel(def component) {
		def button = new JButton("...")
		def panel = new JPanel(new MigLayout("", "[10][grow,fill][][10]", "[grow,fill][][grow,fill]"))
		panel.add component, "cell 1 1"
		panel.add button, "cell 2 1"
		panel
	}

	FileTextField createFileField() {
		def field = FileTextField.create()
		field.setName FILE_FIELD_NAME
		return field
	}
}
