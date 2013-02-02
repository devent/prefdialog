package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;

@SuppressWarnings("serial")
public class ForwardAction extends AbstractToolAction {

	public ForwardAction() {
		setText("Go Forward");
		setMnemonic(KeyEvent.VK_F);
	}

	@Override
	public String getImageResource() {
		return "action_go_next";
	}

	@Override
	public boolean isToggleButton() {
		return false;
	}

	@Override
	public boolean isSeparator() {
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (directoyModel.canGoForward()) {
			fileModel.setDirectory(directoyModel.goForward());
		}
	}

}
