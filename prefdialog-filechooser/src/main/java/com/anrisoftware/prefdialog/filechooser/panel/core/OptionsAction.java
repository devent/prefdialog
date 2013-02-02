package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class OptionsAction extends AbstractToolAction {

	public OptionsAction() {
		setText("Options");
		setMnemonic(KeyEvent.VK_O);
	}

	@Override
	public String getImageResource() {
		return "action_configure";
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
