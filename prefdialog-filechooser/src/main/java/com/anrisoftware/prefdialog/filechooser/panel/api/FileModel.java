package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;

import javax.swing.ListModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

@SuppressWarnings("rawtypes")
public interface FileModel extends ListModel {

	void addChoosableFileFilter(FileFilter filter);

	boolean removeChoosableFileFilter(FileFilter filter);

	FileFilter[] getChoosableFileFilters();

	FileFilter getAcceptAllFileFilter();

	void setFileSystemView(FileSystemView view);

	FileSystemView getFileSystemView();

	void setFileView(FileView view);

	FileView getFileView();

	void setFileHidingEnabled(boolean b);

	boolean isFileHidingEnabled();

	void setDirectory(File directory);
}
