package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.FileChooserUI;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

@SuppressWarnings({ "serial", "rawtypes" })
public class DefaultFileModel extends DefaultListModel implements FileModel {

	private FileSystemView systemView;

	private FileView fileView;

	private final List<FileFilter> filters;

	public DefaultFileModel() {
		this(FileSystemView.getFileSystemView(), null);
	}

	public DefaultFileModel(FileSystemView systemView) {
		this(systemView, null);
	}

	public DefaultFileModel(FileSystemView systemView, FileView fileView) {
		this.filters = new ArrayList<FileFilter>();
		this.systemView = systemView;
		this.fileView = fileView;
		if (fileView == null) {
			fileView = getDefaultFileView();
		}
	}

	private static FileView getDefaultFileView() {
		JFileChooser chooser = new JFileChooser();
		FileChooserUI ui = (FileChooserUI) ComponentUI.createUI(chooser);
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
		FileChooserUI ui = (FileChooserUI) ComponentUI.createUI(chooser);
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFileHidingEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
