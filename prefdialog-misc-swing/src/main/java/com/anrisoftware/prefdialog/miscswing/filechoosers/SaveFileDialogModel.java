/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.miscswing.filechoosers.FileFilterExtension.appendExtensionToFile;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Opens the file save chooser dialog.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings("serial")
public class SaveFileDialogModel implements FileChooserModel {

    private final VetoableChangeSupport vetoableChange;

    private final PropertyChangeSupport propertySupport;

    private JFileChooser chooser;

    private File file;

    private FileFilter fileFilter;

    private boolean attachFileExtension;

    public SaveFileDialogModel() {
        this.vetoableChange = new VetoableChangeSupport(this);
        this.propertySupport = new PropertyChangeSupport(this);
        this.attachFileExtension = true;
    }

    @Override
    public void setFileChooser(JFileChooser chooser) {
        this.chooser = chooser;
    }

    @Override
    public JFileChooser getFileChooser() {
        return chooser;
    }

    /**
     * Attach automatically the selected file extension.
     *
     * @param attach
     *            set to {@code true} to automatically attach the selected file
     *            extension.
     *
     * @since 3.1
     */
    public void setAttachFileExtension(boolean attach) {
        this.attachFileExtension = attach;
    }

    @Override
    public void openDialog(Component parent) throws PropertyVetoException {
        int result = chooser.showSaveDialog(parent);
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
        this.file = attachFileExtension(file);
        updateSelectedFile(file);
        vetoableChange.fireVetoableChange(FILE_PROPERTY, oldValue, file);
    }

    private File attachFileExtension(File file) {
        if (file == null || fileFilter == null || !attachFileExtension) {
            return file;
        }
        if (fileFilter instanceof FileFilterExtension) {
            FileFilterExtension f = (FileFilterExtension) fileFilter;
            return f.appendExtension(file);
        }
        if (fileFilter instanceof FileNameExtensionFilter) {
            FileNameExtensionFilter f = (FileNameExtensionFilter) fileFilter;
            if (f.getExtensions().length > 0) {
                String extension = f.getExtensions()[0];
                return appendExtensionToFile(file, extension);
            }
        }
        return file;
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
