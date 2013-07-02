package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.Status.APPROVED;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractResourcesAction;

/**
 * Imports CSV data and closes the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class ImportAction extends AbstractResourcesAction {

	private static final String ACTION_NAME = "import";

	private CsvImportDialog dialog;

	ImportAction() {
		super(ACTION_NAME);
	}

	public void setDialog(CsvImportDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			dialog.setStatus(APPROVED);
			dialog.closeDialog();
		} catch (PropertyVetoException e) {
		}
	}

}
