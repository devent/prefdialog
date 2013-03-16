package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionEvent;


class SortFolderFirstAction extends AbstractSortingAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setFolderFirstSort(!fileModel.isFolderFirstSort());
	}

}
