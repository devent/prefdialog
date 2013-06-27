package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractResourcesAction;

@SuppressWarnings("serial")
public class CancelAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "cancel";

	private CsvImportDialog dialog;

	CancelAction() {
		super(ACTION_NAME);
	}

	public void setDialog(CsvImportDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.closeDialog();
	}

}
