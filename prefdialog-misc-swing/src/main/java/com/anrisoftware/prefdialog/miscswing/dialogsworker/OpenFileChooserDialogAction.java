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
package com.anrisoftware.prefdialog.miscswing.dialogsworker;

import java.beans.PropertyVetoException;
import java.io.File;

import javax.inject.Inject;
import javax.swing.JFileChooser;

import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;

/**
 * Opens the open file chooser dialog on the AWT event thread and waits for the
 * user to either cancel it or select a file.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public class OpenFileChooserDialogAction extends
        OpenDialogAction<JFileChooser, File> {

    @Inject
    private OpenFileChooserDialogActionLogger log;

    private FileChooserModel model;

    private File file;

    @Inject
    OpenFileChooserDialogAction(FileChooserCreateWorker dialogWorker) {
        setDialogWorker(dialogWorker);
    }

    /**
     * Sets the open file dialog model.
     *
     * @param model
     *            the {@link FileChooserModel}.
     */
    public void setModel(FileChooserModel model) {
        this.model = model;
    }

    /**
     * Sets the last selected file.
     *
     * @param file
     *            the {@link File}.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the selected file.
     *
     * @return the {@link File}.
     */
    public File getFile() {
        return file;
    }

    @Override
    protected File openDialogAWT(JFileChooser dialog,
            AbstractCreateDialogWorker<JFileChooser> dialogWorker) {
        try {
            model.setFileChooser(dialog);
            model.setFile(file);
            model.openDialog(getComponentParent());
        } catch (PropertyVetoException e) {
            log.propertyVeto(e);
        }
        this.file = model.getFile();
        return file;
    }

}
