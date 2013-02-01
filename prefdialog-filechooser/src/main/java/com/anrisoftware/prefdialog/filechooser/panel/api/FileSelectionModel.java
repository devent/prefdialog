package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public interface FileSelectionModel extends ListSelectionModel {

	@SuppressWarnings("rawtypes")
	void setList(JList list);

	File getCurrentDirectory();

	void changeToParentDirectory();

	void setSelectedFile(File file);

	File getSelectedFile();

	void setSelectedFiles(File[] selectedFiles);

	File[] getSelectedFiles();

	List<File> getSelectedFileList();

	void setDirectorySelectionEnabled(boolean enabled);

	boolean isDirectorySelectionEnabled();

	void setFileSelectionEnabled(boolean enabled);

	boolean isFileSelectionEnabled();

	void setMultiSelectionEnabled(boolean b);

	boolean isMultiSelectionEnabled();

}
