/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-csvimportdialog.
 * 
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.CANCEL_ACTION_NAME;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.CANCELED;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Closes the dialog with the canceled status.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class CancelAction extends AbstractResourcesAction {

	private SimpleDialog dialog;

	CancelAction() {
		super(CANCEL_ACTION_NAME);
	}

	public void setDialog(SimpleDialog dialog) {
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
