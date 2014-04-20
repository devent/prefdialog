/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser

import javax.swing.JFileChooser

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.FileChooser
import com.anrisoftware.prefdialog.annotations.FileChooserModel

/**
 * Bean with file chooser fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileChooserBean {

	static final NULL_VALUE = "nullValue"

	static final NO_MODEL = "noModel"

	static final INITIAL_VALUE = "initialValue"

	static final BUTTON_ICON_RESOURCE = "buttonIconResource"

	@FieldComponent
	@FileChooser(model = "fileModel")
	public File nullValue

	@FieldComponent
	@FileChooser
	public File noModel = new File("aaa.txt")

	@FieldComponent
	@FileChooser(model = "fileModel")
	public File initialValue = new File("aaa.txt")

	@FieldComponent
	@FileChooser(model = "fileModel", buttonIcon = "open_icon", buttonTextPosition = TextPosition.ICON_ONLY)
	public File buttonIconResource = new File("aaa.txt")

	public FileChooserModel fileModel = new OpenFileDialogModel()

	FileChooserBean() {
		def chooser = new JFileChooser()
		chooser.setDialogType JFileChooser.OPEN_DIALOG
		fileModel.setFileChooser chooser
	}
}

