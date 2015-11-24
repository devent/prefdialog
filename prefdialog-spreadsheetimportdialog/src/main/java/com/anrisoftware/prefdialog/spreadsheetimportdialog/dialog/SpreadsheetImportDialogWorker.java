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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Dimension;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.anrisoftware.globalpom.spreadsheetimport.DefaultSpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.AbstractCreateDialogWorker;
import com.google.inject.Injector;

/**
 * Creates the spreadsheet import dialog on the AWT event thread.
 *
 * @see SpreadsheetImportDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
public class SpreadsheetImportDialogWorker extends
        AbstractCreateDialogWorker<JDialog> {

    @Inject
    private SpreadsheetImportDialogFactory spreadsheetImportDialogFactory;

    @Inject
    private DefaultSpreadsheetImportProperties properties;

    @Inject
    private Injector parent;

    private Dimension size;

    private JFrame frame;

    private URI previousFile;

    private SpreadsheetImportProperties savedProperties;

    private SpreadsheetImporterFactory importerFactory;

    private SpreadsheetImportDialog importDialog;

    /**
     * Sets the parent Guice injector.
     *
     * @param parent
     *            the parent {@link Inject}.
     */
    public void setParent(Injector parent) {
        this.parent = parent;
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
     * Sets the parent frame for the spreadsheet import dialog.
     *
     * @param frame
     *            the parent {@link JFrame}.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
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
     * Returns the created spreadsheet import dialog.
     *
     * @return the {@link SpreadsheetImportDialog}.
     */
    public synchronized SpreadsheetImportDialog getImportDialog() {
        notNull(importDialog, "Import dialog not created");
        return importDialog;
    }

    @Override
    protected JDialog createDialog() {
        JDialog jdialog = new JDialog(frame, true);
        jdialog.setLocale(getLocale());
        SpreadsheetImportDialog importDialog;
        importDialog = spreadsheetImportDialogFactory.create(savedProperties);
        importDialog.setParent(parent);
        importDialog.setDialog(jdialog);
        importDialog.createDialog(frame, importerFactory);
        setupSavedProperties(properties, savedProperties);
        importDialog.setPropertiesNoChecks(properties);
        importDialog.getDialog().pack();
        importDialog.getDialog().setSize(size);
        this.importDialog = importDialog;
        return jdialog;
    }

    private DefaultSpreadsheetImportProperties setupSavedProperties(
            DefaultSpreadsheetImportProperties properties,
            SpreadsheetImportProperties savedProperties) {
        if (previousFile != null) {
            properties.setFile(previousFile);
        }
        if (savedProperties != null) {
            URI file = savedProperties.getFile();
            if (file != null) {
                properties.setFile(new File(file).getParentFile().toURI());
            }
            properties.setSheetNumber(savedProperties.getSheetNumber());
            properties.setColumns(savedProperties.getColumns());
            properties.setHaveHeader(savedProperties.isHaveHeader());
            properties.setStartRow(savedProperties.getStartRow());
            properties.setEndRow(savedProperties.getEndRow());
        }
        return properties;
    }

}
