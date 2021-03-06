/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.filechoosers;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.File;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Dialog that can select a file.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface FileChooserModel extends Serializable {

    /**
     * The selected file.
     */
    static final String FILE_PROPERTY = "file";

    /**
     * The selected file filter.
     */
    static final String FILE_FILTER_PROPERTY = "file_filter";

    /**
     * Opens the dialog under the parent component for the user to select the
     * file.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT thread.
     * </p>
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
     * @return the {@link File} or {@code null} if the dialog was canceled.
     */
    File getFile();

    /**
     * Sets the current file filter. The property change listener should be
     * informed with the {@link #FILE_FILTER_PROPERTY} property.
     *
     * @param filter
     *            the {@link FileFilter}.
     */
    void setFileFilter(FileFilter filter);

    /**
     * Returns the current file filter.
     *
     * @return the {@link FileFilter} or {@code null} if the dialog was
     *         canceled.
     */
    FileFilter getFileFilter();

    /**
     * Sets the file chooser dialog that will be open.
     *
     * @param chooser
     *            the {@link JFileChooser}.
     */
    void setFileChooser(JFileChooser chooser);

    /**
     * Returns the file chooser dialog that will be open.
     *
     * @return the {@link JFileChooser}.
     */
    JFileChooser getFileChooser();

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
     * @see #FILE_FILTER_PROPERTY
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
     * @see #FILE_FILTER_PROPERTY
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see #FILE_FILTER_PROPERTY
     */
    void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener);

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see #FILE_FILTER_PROPERTY
     */
    void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener);

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
