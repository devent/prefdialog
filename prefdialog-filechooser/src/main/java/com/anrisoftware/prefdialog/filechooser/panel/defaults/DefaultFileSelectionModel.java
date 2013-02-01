package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	private final FileSystemView systemView;
	@SuppressWarnings("rawtypes")
	private JList list;

	public DefaultFileSelectionModel() {
		this(FileSystemView.getFileSystemView());
	}

	public DefaultFileSelectionModel(FileSystemView systemView) {
		this.systemView = systemView;
		setDirectorySelectionEnabled(false);
		setFileSelectionEnabled(true);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void setList(JList list) {
		this.list = list;
	}

	@Override
	public File getCurrentDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeToParentDirectory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectedFile(File file) {
	}

	@Override
	public File getSelectedFile() {
		List<File> fileList = getSelectedFileList();
		return fileList.size() > 0 ? fileList.get(0) : null;
	}

	@Override
	public void setSelectedFiles(File[] selectedFiles) {
		setSelectedFiles(Arrays.asList(selectedFiles));
	}

	@Override
	public void setSelectedFiles(List<File> selectedFiles) {
		if (!isMultiSelectionEnabled()) {
			removeExceptLast(selectedFiles);
		}
		@SuppressWarnings("rawtypes")
		ListModel model = list.getModel();
		for (File file : selectedFiles) {
			for (int i = 0; i < model.getSize(); i++) {
				File fileB = (File) model.getElementAt(i);
				boolean fileSystem = systemView.isFileSystem(file);
				boolean traversable = systemView.isTraversable(file);
				if (fileB.equals(file)) {
					if (fileSelection && fileSystem && !traversable) {
						addSelectionInterval(i, i);
					}
					if (directorySelection && fileSystem && traversable) {
						addSelectionInterval(i, i);
					}
				}
			}
		}
	}

	private void removeExceptLast(List<File> selectedFiles) {
		File last = last(selectedFiles);
		selectedFiles.clear();
		if (last != null) {
			selectedFiles.add(last);
		}
	}

	private File last(List<File> files) {
		return files.size() > 0 ? files.get(files.size() - 1) : null;
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
