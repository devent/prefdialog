package com.anrisoftware.prefdialog.filechooser.panel.core;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.OPTIONS_BUTTON_NAME;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class OptionsAction extends AbstractToolAction {

	public OptionsAction() {
		setText("Options");
		setMnemonic(KeyEvent.VK_O);
	}

	@Override
	public String getName() {
		return OPTIONS_BUTTON_NAME;
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
