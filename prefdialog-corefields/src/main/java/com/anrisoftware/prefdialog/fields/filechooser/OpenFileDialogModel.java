/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.annotations.FileChooserModel;

/**
 * Opens the file open chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class OpenFileDialogModel implements FileChooserModel {

    private final VetoableChangeSupport vetoableChange;

    private final PropertyChangeSupport propertySupport;

    private JFileChooser chooser;

    private File file;

    private FileFilter fileFilter;

    public OpenFileDialogModel() {
        this.vetoableChange = new VetoableChangeSupport(this);
        this.propertySupport = new PropertyChangeSupport(this);
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

    /**
     * Returns the file chooser dialog that will be open.
     * 
     * @return the {@link JFileChooser}.
     */
    public JFileChooser getFileChooser() {
        return chooser;
    }

    @Override
    public void openDialog(Component parent) throws PropertyVetoException {
        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            setFileFilter(chooser.getFileFilter());
            setFile(chooser.getSelectedFile());
        } else {
            setFileFilter(null);
            setFile(null);
        }
    }

    @Override
    public void setFileFilter(FileFilter filter) {
        Object oldValue = this.fileFilter;
        this.fileFilter = filter;
        propertySupport.firePropertyChange(FILE_FILTER_PROPERTY, oldValue,
                filter);
    }

    @Override
    public FileFilter getFileFilter() {
        return fileFilter;
    }

    @Override
    public void setFile(File file) throws PropertyVetoException {
        File oldValue = this.file;
        this.file = file;
        updateSelectedFile(file);
        vetoableChange.fireVetoableChange(FILE_PROPERTY, oldValue, file);
    }

    private void updateSelectedFile(final File file) {
        if (file == null) {
            return;
        }
        invokeLater(new Runnable() {

            @Override
            public void run() {
                if (file.isDirectory()) {
                    chooser.setCurrentDirectory(file);
                } else {
                    chooser.setSelectedFile(file);
                }
            }
        });
    }

    @Override
    public File getFile() {
        return file;
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    @Override
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    @Override
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(propertyName, listener);
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
