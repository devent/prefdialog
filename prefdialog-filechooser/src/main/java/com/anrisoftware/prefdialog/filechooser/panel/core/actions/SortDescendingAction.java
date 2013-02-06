package com.anrisoftware.prefdialog.filechooser.panel.core.actions;

import java.awt.event.ActionEvent;

class SortDescendingAction extends AbstractOptionMenuAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setDescendingSort(!fileModel.isDescendingSort());
	}

}
