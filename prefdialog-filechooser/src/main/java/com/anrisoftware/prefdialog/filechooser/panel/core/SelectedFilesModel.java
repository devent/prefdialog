package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.io.File;
import java.util.List;

import javax.swing.AbstractListModel;

@SuppressWarnings({ "serial", "rawtypes" })
class SelectedFilesModel extends AbstractListModel {

	private List<List<File>> files;

	/**
	 * Sets the selected files.
	 * 
	 * @param files
	 *            a {@link List} of the selected files.
	 */
	public void setSelectedFiles(List<List<File>> files) {
		this.files = files;
		fireContentsChanged(this, 0, getSize());
	}

	@Override
	public int getSize() {
		return files.size();
	}

	/**
	 * A {@link List} of the selected files.
	 */
	@Override
	public Object getElementAt(int index) {
		return files.get(index);
	}

}
