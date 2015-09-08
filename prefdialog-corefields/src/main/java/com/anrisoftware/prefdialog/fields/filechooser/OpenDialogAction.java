/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;

/**
 * Opens the file chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class OpenDialogAction implements ActionListener {

	private FileChooserModel model;

	/**
	 * Sets the model that will open the file chooser dialog.
	 * 
	 * @param model
	 *            the {@link FileChooserModel}.
	 */
	public void setFileChooserModel(FileChooserModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			model.openDialog((Component) e.getSource());
		} catch (PropertyVetoException e1) {
		}
	}

}
