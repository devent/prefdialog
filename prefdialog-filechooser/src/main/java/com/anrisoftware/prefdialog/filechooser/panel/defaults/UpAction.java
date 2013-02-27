package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.UP_BUTTON_NAME;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;

@SuppressWarnings("serial")
public class UpAction extends AbstractToolAction {

	public UpAction() {
		setText("Go Up");
		setMnemonic(KeyEvent.VK_U);
	}

	@Override
	public String getName() {
		return UP_BUTTON_NAME;
	}

	@Override
	public String getImageResource() {
		return "action_go_up";
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
		if (directoyModel.canGoUp()) {
			fileSelectionModel.clearSelection();
			fileModel.setDirectory(directoyModel.goUp());
		}
	}

}
