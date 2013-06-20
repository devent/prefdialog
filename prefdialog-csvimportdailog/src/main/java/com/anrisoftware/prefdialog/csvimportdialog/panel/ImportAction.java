package com.anrisoftware.prefdialog.csvimportdialog.panel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.anrisoftware.resources.texts.central.TextsResources;

@SuppressWarnings("serial")
public class ImportAction extends AbstractAction {

	private static final String ACTION_NAME = "import";

	public void setTexts(TextsResources texts) {
		putValue(NAME, texts.getAction(ACTION_NAME));
		putValue(MNEMONIC_KEY, texts.getMnemonic(ACTION_NAME));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
