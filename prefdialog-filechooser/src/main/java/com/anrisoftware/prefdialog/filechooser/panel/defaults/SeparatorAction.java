package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeparatorAction extends AbstractToolAction {

	public SeparatorAction() {
		setText("");
	}

	@Override
	public boolean isToggleButton() {
		return false;
	}

	@Override
	public boolean isSeparator() {
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
