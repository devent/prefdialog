package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHOW_PREVIEW_BUTTON_NAME;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;

@SuppressWarnings("serial")
public class ShowPreviewAction extends AbstractToolAction {

	public ShowPreviewAction() {
		setText("Show Prewiew");
		setMnemonic(KeyEvent.VK_P);
	}

	@Override
	public String getName() {
		return SHOW_PREVIEW_BUTTON_NAME;
	}

	@Override
	public String getImageResource() {
		return "action_document_preview";
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
