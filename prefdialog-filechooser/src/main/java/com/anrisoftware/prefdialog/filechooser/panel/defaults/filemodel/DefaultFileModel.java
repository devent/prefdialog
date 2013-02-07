package com.anrisoftware.prefdialog.filechooser.panel.defaults.filemodel;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSort;

@SuppressWarnings({ "serial", "rawtypes" })
public class DefaultFileModel extends AbstractListModel implements FileModel {

	private final PropertyChangeSupport support;

	private FileSystemView systemView;

	private FileView fileView;

	private final List<FileFilter> filters;

	private boolean useFileHiding;

	private List<File> files;

	private File directory;

	private final FileSorting fileSorting;

	public DefaultFileModel() {
		this(FileSystemView.getFileSystemView(), null);
	}

	public DefaultFileModel(FileSystemView systemView) {
		this(systemView, null);
	}

	public DefaultFileModel(FileSystemView systemView, FileView fileView) {
		this.fileSorting = new FileSorting(this);
		this.support = new PropertyChangeSupport(this);
		this.filters = new ArrayList<FileFilter>();
		this.files = new ArrayList<File>();
		this.useFileHiding = true;
		this.systemView = systemView;
		this.fileView = createFileView(fileView);
		fileSorting.setFileSort(FileSort.NAME);
		fileSorting.setDescending(false);
		fileSorting.setFolderFirst(true);
	}

	private FileView createFileView(FileView fileView) {
		if (fileView == null) {
			fileView = getDefaultFileView();
		}
		return fileView;
	}

	private static FileView getDefaultFileView() {
		JFileChooser chooser = new JFileChooser();
		FileChooserUI ui = (FileChooserUI) BasicFileChooserUI.createUI(chooser);
		ui.installUI(chooser);
		return ui.getFileView(chooser);
	}

	@Override
	public void addChoosableFileFilter(FileFilter filter) {
		filters.add(filter);
	}

	@Override
	public boolean removeChoosableFileFilter(FileFilter filter) {
		return filters.remove(filter);
	}

	@Override
	public List<FileFilter> getChoosableFileFilters() {
		return filters;
	}

	@Override
	public FileFilter getAcceptAllFileFilter() {
		JFileChooser chooser = new JFileChooser();
		FileChooserUI ui = (FileChooserUI) BasicFileChooserUI.createUI(chooser);
		return ui.getAcceptAllFileFilter(chooser);
	}

	@Override
	public void setFileSystemView(FileSystemView view) {
		this.systemView = view;
	}

	@Override
	public FileSystemView getFileSystemView() {
		return systemView;
	}

	@Override
	public void setFileView(FileView view) {
		this.fileView = view;
	}

	@Override
	public FileView getFileView() {
		return fileView;
	}

	@Override
	public void setFileHidingEnabled(boolean b) {
		boolean oldValue = this.useFileHiding;
		this.useFileHiding = b;
		support.firePropertyChange(FILE_HIDDING_PROPERTY, oldValue, b);
	}

	@Override
	public boolean isFileHidingEnabled() {
		return useFileHiding;
	}

	@Override
	public void setDirectory(File directory) {
		File oldValue = this.directory;
		if (!directory.equals(oldValue)) {
			this.directory = directory;
			updateDirectory();
		}
		support.firePropertyChange(DIRECTORY_PROPERTY, oldValue, directory);
	}

	private void updateDirectory() {
		updateDirectory(fileSorting.getComparator());
	}

	void updateDirectory(Comparator<File> comparator) {
		if (directory == null) {
			return;
		}
		List<File> list = asList(systemView.getFiles(directory, useFileHiding));
		sort(list, comparator);
		this.files = list;
		fireContentsChanged(this, 0, getSize());
	}

	@Override
	public File getDirectory() {
		return directory;
	}

	@Override
	public void setFileSort(FileSort sort) {
		FileSort oldValue = fileSorting.getFileSort();
		fileSorting.setFileSort(sort);
		support.firePropertyChange(FILE_SORT, oldValue, sort);
	}

	@Override
	public FileSort getFileSort() {
		return fileSorting.getFileSort();
	}

	@Override
	public void setDescendingSort(boolean descending) {
		fileSorting.setDescending(descending);
	}

	@Override
	public boolean isDescendingSort() {
		return fileSorting.isDescending();
	}

	@Override
	public void setFolderFirstSort(boolean folderFirst) {
		fileSorting.setFolderFirst(folderFirst);
	}

	@Override
	public boolean isFolderFirstSort() {
		return fileSorting.isFolderFirst();
	}

	@Override
	public int getSize() {
		return files.size();
	}

	@Override
	public Object getElementAt(int index) {
		return files.get(index);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	@Override
	public void addPropertyChangeListener(String name, PropertyChangeListener l) {
		support.addPropertyChangeListener(name, l);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

	@Override
	public void removePropertyChangeListener(String name,
			PropertyChangeListener l) {
		support.removePropertyChangeListener(name, l);
	}
}
