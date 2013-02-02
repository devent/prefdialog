package com.anrisoftware.prefdialog.filechooser.panel.api;

import javax.swing.ListModel;

public interface ToolButtonsModel extends ListModel<ToolAction> {

	ToolAction getActionAt(int index);
}
