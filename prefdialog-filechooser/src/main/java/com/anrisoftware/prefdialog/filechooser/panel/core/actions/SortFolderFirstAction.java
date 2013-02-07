package com.anrisoftware.prefdialog.filechooser.panel.core.actions;

import java.awt.event.ActionEvent;

class SortFolderFirstAction extends AbstractOptionMenuAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		fileModel.setFolderFirstSort(!fileModel.isFolderFirstSort());
	}

}
