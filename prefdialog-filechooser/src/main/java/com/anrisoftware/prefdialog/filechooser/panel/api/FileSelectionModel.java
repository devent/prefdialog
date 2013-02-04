package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileSystemView;

public interface FileSelectionModel extends ListSelectionModel {

	void setFileSystemView(FileSystemView systemView);

	@SuppressWarnings("rawtypes")
	void setList(JList list);

	void setSelectedFile(File file);

	File getSelectedFile();

	void setSelectedFiles(File[] files);

	void setSelectedFiles(Collection<File> files);

	File[] getSelectedFiles();

	List<File> getSelectedFileList();

	void setDirectorySelectionEnabled(boolean enabled);

	boolean isDirectorySelectionEnabled();

	void setFileSelectionEnabled(boolean enabled);

	boolean isFileSelectionEnabled();

	void setMultiSelectionEnabled(boolean enabled);

	boolean isMultiSelectionEnabled();

}
