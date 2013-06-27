package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import java.io.File;

import javax.inject.Inject;
import javax.swing.JFileChooser;

import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.anrisoftware.prefdialog.fields.filechooser.OpenFileDialogModel;

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
	@FileChooser(model = "fileModel")
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
