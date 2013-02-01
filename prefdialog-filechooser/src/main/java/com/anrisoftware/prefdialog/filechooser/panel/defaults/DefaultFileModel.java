package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

@SuppressWarnings({ "serial", "rawtypes" })
public class DefaultFileModel extends AbstractListModel implements FileModel {

	private FileSystemView systemView;

	private FileView fileView;

	private final List<FileFilter> filters;

	private boolean useFileHiding;

	private List<File> files;

	public DefaultFileModel() {
		this(FileSystemView.getFileSystemView(), null);
	}

	public DefaultFileModel(FileSystemView systemView) {
		this(systemView, null);
	}

	public DefaultFileModel(FileSystemView systemView, FileView fileView) {
		this.filters = new ArrayList<FileFilter>();
		this.files = new ArrayList<File>();
		this.useFileHiding = true;
		this.systemView = systemView;
		this.fileView = createFileView(fileView);
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
	public FileFilter[] getChoosableFileFilters() {
		return filters.toArray(new FileFilter[filters.size()]);
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
		this.useFileHiding = b;
	}

	@Override
	public boolean isFileHidingEnabled() {
		return useFileHiding;
	}

	@Override
	public void setDirectory(File directory) {
		files = Arrays.asList(systemView.getFiles(directory, useFileHiding));
		fireContentsChanged(this, 0, getSize());
	}

	@Override
	public int getSize() {
		return files.size();
	}

	@Override
	public Object getElementAt(int index) {
		return files.get(index);
	}

}
