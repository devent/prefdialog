package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileSort;

class SortDateAction extends AbstractSortingAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setFileSort(FileSort.DATE);
	}

}
