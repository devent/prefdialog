/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.Status.CANCELED;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

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
	public void actionPerformed(ActionEvent evt) {
		try {
			dialog.setStatus(CANCELED);
			dialog.closeDialog();
		} catch (PropertyVetoException e) {
		}
	}

}
