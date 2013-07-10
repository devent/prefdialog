/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.File;

import javax.swing.JFileChooser;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.annotations.FileChooserModel;

/**
 * Opens the file chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class OpenFileDialogModel implements FileChooserModel {

	private final VetoableChangeSupport vetoableChange;

	private JFileChooser chooser;

	private File file;

	public OpenFileDialogModel() {
		this.vetoableChange = new VetoableChangeSupport(this);
	}

	/**
	 * Sets the file chooser dialog that will be open.
	 * 
	 * @param chooser
	 *            the {@link JFileChooser}.
	 */
	public void setFileChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}

	@Override
	public void openDialog(Component parent) throws PropertyVetoException {
		int result = chooser.showDialog(parent, null);
		if (result == JFileChooser.APPROVE_OPTION) {
			setFile(chooser.getSelectedFile());
		}
	}

	@Override
	public void setFile(File file) throws PropertyVetoException {
		File oldValue = this.file;
		this.file = file;
		chooser.setSelectedFile(file);
		vetoableChange.fireVetoableChange(FILE_PROPERTY, oldValue, file);
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChange.addVetoableChangeListener(listener);
	}

	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChange.removeVetoableChangeListener(listener);
	}

	@Override
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableChange.addVetoableChangeListener(propertyName, listener);
	}

	@Override
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableChange.removeVetoableChangeListener(propertyName, listener);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
