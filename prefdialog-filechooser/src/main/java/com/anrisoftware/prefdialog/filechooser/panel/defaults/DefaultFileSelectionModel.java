package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.ArrayUtils;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;

@SuppressWarnings("serial")
public class DefaultFileSelectionModel extends DefaultListSelectionModel
		implements FileSelectionModel {

	private boolean directorySelection;

	private boolean fileSelection;

	private FileSystemView systemView;

	@SuppressWarnings("rawtypes")
	private JList list;

	public DefaultFileSelectionModel() {
		setFileSystemView(FileSystemView.getFileSystemView());
		setDirectorySelectionEnabled(false);
		setFileSelectionEnabled(true);
	}

	@Override
	public void setFileSystemView(FileSystemView systemView) {
		this.systemView = systemView;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void setList(JList list) {
		this.list = list;
	}

	@Override
	public void setSelectedFile(File file) {
		setSelectedFiles(Arrays.asList(file));
	}

	@Override
	public File getSelectedFile() {
		List<File> fileList = getSelectedFileList();
		return fileList.size() > 0 ? fileList.get(0) : null;
	}

	@Override
	public void setSelectedFiles(File[] files) {
		setSelectedFiles(Arrays.asList(files));
	}

	@Override
	public void setSelectedFiles(Collection<File> files) {
		if (fileSelection) {
			files = removeDirectories(files);
		}
		if (!isMultiSelectionEnabled()) {
			files = removeExceptFirst(files);
		}
		@SuppressWarnings("rawtypes")
		ListModel model = list.getModel();
		int lastIndex = -1;
		for (File file : files) {
			for (int i = 0; i < model.getSize(); i++) {
				File fileB = (File) model.getElementAt(i);
				boolean fileSystem = systemView.isFileSystem(file);
				boolean traversable = systemView.isTraversable(file);
				if (fileB.equals(file)) {
					if (fileSelection && fileSystem && !traversable) {
						addSelectionInterval(i, i);
						lastIndex = i;
					}
					if (directorySelection && fileSystem && traversable) {
						addSelectionInterval(i, i);
						lastIndex = i;
					}
				}
			}
		}
		if (lastIndex != -1) {
			list.ensureIndexIsVisible(lastIndex);
		}
	}

	private List<File> removeDirectories(Collection<File> files) {
		List<File> list = new ArrayList<File>(files.size());
		for (File file : files) {
			if (!systemView.isTraversable(file)) {
				list.add(file);
			}
		}
		return list;
	}

	private List<File> removeExceptFirst(Collection<File> files) {
		List<File> list = new ArrayList<File>(1);
		File last = first(files);
		if (last != null) {
			list.add(last);
		}
		return list;
	}

	private File first(Collection<File> files) {
		Iterator<File> it = files.iterator();
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	@Override
	public File[] getSelectedFiles() {
		List<File> fileList = getSelectedFileList();
		return fileList.toArray(new File[fileList.size()]);
	}

	@Override
	public List<File> getSelectedFileList() {
		@SuppressWarnings("deprecation")
		Object[] values = list.getSelectedValues();
		List<File> fileList = new ArrayList<File>();
		if (ArrayUtils.isEmpty(values)) {
			return fileList;
		}
		for (Object value : values) {
			File file = (File) value;
			boolean fileSystem = systemView.isFileSystem(file);
			boolean traversable = systemView.isTraversable(file);
			if (fileSelection && fileSystem && !traversable) {
				fileList.add(file);
			}
			if (directorySelection && fileSystem && traversable) {
				fileList.add(file);
			}
		}
		return fileList;
	}

	@Override
	public void setDirectorySelectionEnabled(boolean enabled) {
		this.directorySelection = enabled;
		if (!enabled && !isFileSelectionEnabled()) {
			setFileSelectionEnabled(true);
		}
	}

	@Override
	public boolean isDirectorySelectionEnabled() {
		return directorySelection;
	}

	@Override
	public void setFileSelectionEnabled(boolean enabled) {
		this.fileSelection = enabled;
		if (!enabled && !isDirectorySelectionEnabled()) {
			setDirectorySelectionEnabled(true);
		}
	}

	@Override
	public boolean isFileSelectionEnabled() {
		return fileSelection;
	}

	@Override
	public void setMultiSelectionEnabled(boolean b) {
		if (b) {
			setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
		} else {
			setSelectionMode(SINGLE_SELECTION);
		}
	}

	@Override
	public boolean isMultiSelectionEnabled() {
		return getSelectionMode() == MULTIPLE_INTERVAL_SELECTION;
	}
}
