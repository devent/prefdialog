package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import javax.swing.DefaultListModel;

import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;

@SuppressWarnings("serial")
public class DefaultToolButtonsModel extends DefaultListModel<ToolAction>
		implements ToolButtonsModel {

	public DefaultToolButtonsModel() {
		addElement(new BackAction());
		addElement(new ForwardAction());
		addElement(new UpAction());
		addElement(new RefreshAction());
		addElement(new SeparatorAction());
		addElement(new ShowPreviewAction());
	}

	@Override
	public ToolAction getActionAt(int index) {
		return getElementAt(index);
	}

}
