package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.Status.CANCELED;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractResourcesAction;

/**
 * Closes the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class CancelAction extends AbstractResourcesAction {

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
		dialog.setStatus(CANCELED);
		dialog.closeDialog();
	}

}
