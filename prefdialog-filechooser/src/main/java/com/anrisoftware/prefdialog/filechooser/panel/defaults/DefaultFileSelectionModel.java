package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.io.File;

import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;

@SuppressWarnings("serial")
public class DefaultFileSelectionModel extends DefaultListSelectionModel
		implements FileSelectionModel, ListSelectionListener {

	DefaultFileSelectionModel() {
		addListSelectionListener(this);
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
		// TODO Auto-generated method stub

	}

	@Override
	public File getSelectedFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedFiles(File[] selectedFiles) {
		// TODO Auto-generated method stub

	}

	@Override
	public File[] getSelectedFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirectorySelectionEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFileSelectionEnabled() {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
