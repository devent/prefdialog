package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ShowPreviewAction extends AbstractToolAction {

	public ShowPreviewAction() {
		setText("Show Prewiew");
		setMnemonic(KeyEvent.VK_P);
	}

	@Override
	public boolean isToggleButton() {
		return true;
	}

	@Override
	public boolean isSeparator() {
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
