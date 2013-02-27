package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.BACK_BUTTON_NAME;
import static java.awt.event.KeyEvent.VK_B;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;

@SuppressWarnings("serial")
public class BackAction extends AbstractToolAction {

	public BackAction() {
		setText("Go Back");
		setMnemonic(VK_B);
	}

	@Override
	public String getName() {
		return BACK_BUTTON_NAME;
	}

	@Override
	public String getImageResource() {
		return "action_go_previous";
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
		if (directoyModel.canGoBack()) {
			fileSelectionModel.clearSelection();
			fileModel.setDirectory(directoyModel.goBack());
		}
	}

}
