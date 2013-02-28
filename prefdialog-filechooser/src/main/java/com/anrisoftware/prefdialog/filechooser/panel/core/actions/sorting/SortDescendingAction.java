package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.actions.options.AbstractOptionMenuAction;

class SortDescendingAction extends AbstractOptionMenuAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setDescendingSort(!fileModel.isDescendingSort());
	}

}
