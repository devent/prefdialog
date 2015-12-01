/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import static javax.swing.SwingUtilities.getWindowAncestor;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.net.URI;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.AbstractCreateDialogWorker;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.CreateDialogWorkerException;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.OpenDialogAction;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.google.inject.Injector;

/**
 * Opens the open spreadsheet import dialog on the AWT event thread and waits
 * for the user to either cancel it or select a file.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class OpenSpreadsheetImportDialogAction extends
        OpenDialogAction<JDialog, SpreadsheetImportProperties> {

    @Inject
    private Injector injector;

    private Dimension size;

    private URI previousFile;

    private SpreadsheetImportProperties savedProperties;

    private SpreadsheetImporterFactory importerFactory;

    private byte[] currentLayout;

    @Inject
    OpenSpreadsheetImportDialogAction(SpreadsheetImportDialogWorker dialogWorker) {
        setDialogWorker(dialogWorker);
    }

    /**
     * Sets the parent Guice injector.
     *
     * @param parent
     *            the parent {@link Inject}.
     */
    public void setParent(Injector parent) {
        this.injector = parent;
    }

    /**
     * Returns the current dialog size.
     *
     * @return the {@link Dimension} size.
     */
    public Dimension getDialogSize() {
        return getDialogWorker().getDialogSize();
    }

    /**
     * Sets the size of the spreadsheet import dialog.
     *
     * @param size
     *            the {@link Dimension} size.
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    /**
     * Sets the previous set file of the spreadsheet import dialog.
     *
     * @param previousFile
     *            the previous set {@link URI} file.
     */
    public void setPreviousFile(URI previousFile) {
        this.previousFile = previousFile;
    }

    /**
     * Sets the saved properties of the spreadsheet import dialog.
     *
     * @param savedProperties
     *            the saved {@link SpreadsheetImportProperties}.
     */
    public void setSavedProperties(SpreadsheetImportProperties savedProperties) {
        this.savedProperties = savedProperties;
    }

    /**
     * Sets the spreadsheet importer factory.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     */
    public void setImporterFactory(SpreadsheetImporterFactory importerFactory) {
        this.importerFactory = importerFactory;
    }

    /**
     * Sets the current layout of the dialog.
     */
    public void setCurrentLayout(byte[] currentLayout) {
        this.currentLayout = currentLayout;
    }

    /**
     * Returns the current layout of the dialog.
     */
    public byte[] getCurrentLayout() {
        SpreadsheetImportDialogWorker worker = getDialogWorker();
        return worker.getCurrentLayout();
    }

    /**
     * Returns {@code true} if the dialog was opened.
     */
    public boolean isDialogOpened() {
        SpreadsheetImportDialogWorker worker = getDialogWorker();
        return worker.getImportDialog() != null;
    }

    @Override
    protected JDialog createDialog(
            AbstractCreateDialogWorker<JDialog> dialogWorker)
            throws CreateDialogWorkerException {
        SpreadsheetImportDialogWorker w = (SpreadsheetImportDialogWorker) dialogWorker;
        w.setParent(injector);
        w.setSize(size);
        Component parent = getComponentParent();
        if (parent instanceof JFrame) {
            w.setParentWindow((Window) parent);
        } else {
            w.setParentWindow(getWindowAncestor(parent));
        }
        w.setPreviousFile(previousFile);
        w.setSavedProperties(savedProperties);
        w.setImporterFactory(importerFactory);
        return super.createDialog(dialogWorker);
    }

    @Override
    protected void setupDialog(JDialog dialog,
            AbstractCreateDialogWorker<JDialog> dialogWorker) {
        SpreadsheetImportDialogWorker w = (SpreadsheetImportDialogWorker) dialogWorker;
        w.setCurrentLayout(currentLayout);
    }

    @Override
    protected SpreadsheetImportProperties openDialogAWT(final JDialog dialog,
            AbstractCreateDialogWorker<JDialog> dialogWorker)
            throws CreateDialogWorkerException {
        SpreadsheetImportDialogWorker w = (SpreadsheetImportDialogWorker) dialogWorker;
        SpreadsheetImportDialog importDialog = w.getImportDialog();
        notNull(importDialog, "importDialog=null");
        dialog.setVisible(true);
        if (importDialog.getStatus() == Status.APPROVED) {
            return importDialog.getProperties();
        } else {
            return null;
        }
    }

}
