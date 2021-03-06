package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.REFRESH_BUTTON_NAME;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;

@SuppressWarnings("serial")
public class RefreshAction extends AbstractToolAction {

	public RefreshAction() {
		setText("Refresh");
		setMnemonic(KeyEvent.VK_R);
	}

	@Override
	public String getName() {
		return REFRESH_BUTTON_NAME;
	}

	@Override
	public String getImageResource() {
		return "action_view_refresh";
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
		fileSelectionModel.clearSelection();
	}

}
