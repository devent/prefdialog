package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionEvent;


class SortDescendingAction extends AbstractSortingAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setDescendingSort(!fileModel.isDescendingSort());
	}

}
