/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.APPROVE_ACTION_NAME;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.APPROVED;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Sets the approval state and closes the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class ApproveAction extends AbstractResourcesAction {

	private SimpleDialog dialog;

	ApproveAction() {
		super(APPROVE_ACTION_NAME);
	}

	public void setDialog(SimpleDialog dialog) {
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
