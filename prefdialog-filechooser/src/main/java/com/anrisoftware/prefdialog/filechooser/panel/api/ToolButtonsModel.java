package com.anrisoftware.prefdialog.filechooser.panel.api;

import javax.swing.Action;
import javax.swing.ListModel;

@SuppressWarnings("rawtypes")
public interface ToolButtonsModel extends ListModel {

	Action getActionAt(int index);
}
