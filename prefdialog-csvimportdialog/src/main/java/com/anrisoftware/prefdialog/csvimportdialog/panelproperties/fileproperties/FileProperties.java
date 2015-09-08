/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.fileproperties;

import java.io.File;

import javax.inject.Inject;
import javax.swing.JFileChooser;

import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;
import com.anrisoftware.prefdialog.miscswing.filechoosers.OpenFileDialogModel;

public class FileProperties {

	private File file;

	private final OpenFileDialogModel fileModel;

	private final JFileChooser fileChooser;

	@Inject
	FileProperties() {
		this.file = new File("");
		this.fileModel = new OpenFileDialogModel();
		this.fileChooser = new JFileChooser();
		fileModel.setFileChooser(fileChooser);
	}

	public void setFile(File file) {
		this.file = file;
	}

	@FieldComponent(showTitle = false)
	@FileChooser(buttonText = "fileButton", buttonMnemonic = "fileButton_mnemonic", model = "fileModel")
	public File getFile() {
		return file;
	}

	public FileChooserModel getFileModel() {
		return fileModel;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}
}
