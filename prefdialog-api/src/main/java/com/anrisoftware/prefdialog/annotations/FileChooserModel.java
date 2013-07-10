/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-api.
 *
 * prefdialog-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.File;
import java.io.Serializable;

/**
 * Dialog that can select a file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FileChooserModel extends Serializable {

	/**
	 * The selected file.
	 */
	static final String FILE_PROPERTY = "file";

	/**
	 * Opens the dialog under the parent component.
	 * 
	 * @param parent
	 *            the parent component or {@code null}.
	 * 
	 * @throws PropertyVetoException
	 *             if the selected file is rejected.
	 */
	void openDialog(Component parent) throws PropertyVetoException;

	/**
	 * Sets the current selected file. The property change listener should be
	 * informed with the {@link #FILE_PROPERTY} property.
	 * 
	 * @param file
	 *            the {@link File}.
	 * 
	 * @throws PropertyVetoException
	 *             if the specified file is rejected.
	 */
	void setFile(File file) throws PropertyVetoException;

	/**
	 * Returns the selected file.
	 * 
	 * @return the {@link File}.
	 */
	File getFile();

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(VetoableChangeListener)
	 * @see #FILE_PROPERTY
	 */
	void addVetoableChangeListener(VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(VetoableChangeListener)
	 * @see #FILE_PROPERTY
	 */
	void removeVetoableChangeListener(VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #FILE_PROPERTY
	 */
	void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #FILE_PROPERTY
	 */
	void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener);

}
