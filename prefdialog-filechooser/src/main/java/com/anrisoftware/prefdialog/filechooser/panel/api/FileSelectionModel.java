package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ListSelectionModel;

public interface FileSelectionModel extends ListSelectionModel {

	File getCurrentDirectory();

	void changeToParentDirectory();

	void setSelectedFile(File file);

	File getSelectedFile();

	void setSelectedFiles(File[] selectedFiles);

	File[] getSelectedFiles();

	boolean isDirectorySelectionEnabled();

	boolean isFileSelectionEnabled();

	void setMultiSelectionEnabled(boolean b);

	boolean isMultiSelectionEnabled();

}
