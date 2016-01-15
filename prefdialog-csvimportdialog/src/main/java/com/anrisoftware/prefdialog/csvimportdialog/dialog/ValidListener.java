/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.fields.FieldComponent.VALUE_PROPERTY;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.STATUS_PROPERTY;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.APPROVED;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;

/**
 * Tests the CSV import dialog for valid input and veto approval of the dialog
 * if not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ValidListener {

	private static final String FILE_FIELD = "file";

	private final VetoableChangeListener valueListener;

	private final VetoableChangeListener statusListener;

	private boolean inputValid;

	ValidListener() {
		this.inputValid = false;
		this.valueListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				FieldComponent<?> field = (FieldComponent<?>) evt.getSource();
				checkValidFile(evt, field);
			}

		};
		this.statusListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				Status status = (CsvImportDialog.Status) evt.getNewValue();
				if (!inputValid && status == APPROVED) {
					throw new PropertyVetoException("Input invalid", evt);
				}
			}
		};
	}

	/**
	 * Adds the change listeners for the specified dialog.
	 * 
	 * @param dialog
	 *            the {@link CsvImportDialog}.
	 */
	public void installDialog(CsvImportDialog dialog) {
		dialog.addVetoableChangeListener(VALUE_PROPERTY, valueListener);
		dialog.addVetoableChangeListener(STATUS_PROPERTY, statusListener);
	}

	/**
	 * Removes the change listeners for the specified dialog.
	 * 
	 * @param dialog
	 *            the {@link CsvImportDialog}.
	 */
	public void uninstallDialog(CsvImportDialog dialog) {
		dialog.removeVetoableChangeListener(VALUE_PROPERTY, valueListener);
		dialog.removeVetoableChangeListener(STATUS_PROPERTY, statusListener);
	}

	private void checkValidFile(PropertyChangeEvent evt, FieldComponent<?> field)
			throws PropertyVetoException {
		if (!field.getName().equals(FILE_FIELD)) {
			return;
		}
		File file = (File) evt.getNewValue();
		inputValid = file != null && file.isFile() && file.canRead();
		if (!inputValid) {
			throw new PropertyVetoException("File expected", evt);
		}
	}

}
