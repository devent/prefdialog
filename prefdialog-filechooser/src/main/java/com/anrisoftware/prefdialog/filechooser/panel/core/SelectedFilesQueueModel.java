package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
class SelectedFilesQueueModel extends DefaultComboBoxModel<Set<File>> {

	/**
	 * Sets the selected files.
	 * 
	 * @param files
	 *            a {@link List} of the selected files.
	 */
	public void setSelectedFiles(List<Set<File>> files) {
		removeAllElements();
		for (Set<File> list : files) {
			addElement(list);
		}
	}

}
