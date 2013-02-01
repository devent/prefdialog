package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	private FileSystemView systemView;

	private FileView fileView;

	private final List<FileFilter> filters;

	private boolean useFileHiding;

	private List<File> files;

	private final FileSort sort;

	private boolean descending;

	private boolean folderFirst;

	private Comparator<File> comparator;

	private File directory;

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
		this.sort = FileSort.NAME;
		this.descending = false;
		this.folderFirst = true;
		this.comparator = new FileByName();
		this.comparator = new FileBySize();
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
		File oldValue = this.directory;
		if (!directory.equals(oldValue)) {
			this.directory = directory;
			updateDirectory(directory);
		}
	}

	@Override
	public void setSort(FileSort sort, boolean descending, boolean folderFirst) {
		this.descending = descending;
		this.folderFirst = folderFirst;
		switch (sort) {
		case NAME:
			this.comparator = new FileByName();
			break;
		case SIZE:
			this.comparator = new FileBySize();
			break;
		case DATE:
			this.comparator = new FileByName();
			break;
		case TYPE:
			this.comparator = new FileByName();
			break;
		}
		updateDirectory(directory);
	}

	private void updateDirectory(File directory) {
		List<File> list = Arrays.asList(systemView.getFiles(directory,
				useFileHiding));
		Collections.sort(list, comparator);
		this.files = list;
		fireContentsChanged(this, 0, getSize());
	}

	private abstract class AbstractFileComparator implements Comparator<File> {

		@Override
		public int compare(File o1, File o2) {
			int mod = 1;
			int compare = 0;
			if (descending) {
				mod = -1;
			}
			if (folderFirst) {
				boolean traversable1 = systemView.isTraversable(o1);
				boolean traversable2 = systemView.isTraversable(o2);
				if (traversable1 && !traversable2) {
					compare = -1;
				} else if (!traversable1 && traversable2) {
					compare = 1;
				} else {
					compare = compareDir(o1, o2);
				}
			} else {
				compare = compareFile(o1, o2);
			}
			return compare * mod;
		}

		protected abstract int compareDir(File o1, File o2);

		protected abstract int compareFile(File o1, File o2);

	}

	private class FileByName extends AbstractFileComparator {

		@Override
		protected int compareDir(File o1, File o2) {
			return compareFile(o1, o2);
		}

		@Override
		protected int compareFile(File o1, File o2) {
			return fileView.getName(o1).compareToIgnoreCase(
					fileView.getName(o2));
		}

	}

	private class FileBySize extends AbstractFileComparator {

		@Override
		protected int compareDir(File o1, File o2) {
			return fileView.getName(o1).compareToIgnoreCase(
					fileView.getName(o2));
		}

		@Override
		protected int compareFile(File o1, File o2) {
			long l1 = o1.length();
			long l2 = o2.length();
			return l1 > l2 ? -1 : l1 < l2 ? 1 : 0;
		}

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
