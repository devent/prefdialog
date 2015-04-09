/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.Dimension;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anrisoftware.globalpom.dataimport.CsvImportProperties;
import com.anrisoftware.globalpom.dataimport.CsvImporter;
import com.anrisoftware.globalpom.dataimport.CsvImporterFactory;
import com.anrisoftware.globalpom.dataimport.DefaultCsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.google.inject.Injector;

/**
 * Creates the CSV import dialog.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
public class OpenCsvImportDialog {

    private static Logger log = LoggerFactory
            .getLogger(OpenCsvImportDialog.class);

    private static Injector injector;

    private static CsvPanelPropertiesFactory propertiesFactory;

    private static CsvPanelProperties bean;

    private static OpenCsvImportDialog importDialog;

    private static JFrame dialogFrame;

    private static Dimension dialogSize;

    private static CsvImportDialog dialog;

    /**
     * Opens the CSV import dialog.
     */
    public static void main(String[] args) {
        dialogSize = new Dimension(600, 480);
        injector = CsvImportDialogModule.getInjector();
        propertiesFactory = injector
                .getInstance(CsvPanelPropertiesFactory.class);
        bean = propertiesFactory.create();
        importDialog = injector.getInstance(OpenCsvImportDialog.class);
        importDialog.setSize(dialogSize);
        importDialog.setPreviousFile(new File(".").toURI());
        importDialog.setSavedProperties(bean);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                dialogFrame = new JFrame("Open CSV Import Dialog");
                dialogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                importDialog.setFrame(dialogFrame);
                dialog = importDialog.createDialog();
                importDialog.openDialog(dialog);
                try {
                    CsvImporter importer = importDialog.createValue(dialog);
                    log.info("Created importer: {}", importer);
                } catch (Exception e) {
                    log.error("Error create importer", e);
                }
                System.exit(0);
            }
        });
    }

    @Inject
    private CsvImportDialogFactory csvImportDialogFactory;

    @Inject
    private DefaultCsvImportProperties properties;

    @Inject
    private CsvImporterFactory csvImporterFactory;

    @Inject
    private Injector parent;

    private JFrame frame;

    private URI previousFile;

    private CsvImportProperties savedProperties;

    private Dimension size;

    /**
     * Creates and returns the CSV import dialog
     */
    public CsvImportDialog createDialog() {
        JDialog jdialog = new JDialog(frame, true);
        CsvImportDialogFactory factory = csvImportDialogFactory;
        CsvImportDialog dialog = factory.create(savedProperties);
        dialog.setParent(parent);
        dialog.setDialog(jdialog);
        dialog.createDialog();
        setupSavedProperties(properties, savedProperties);
        dialog.setPropertiesNoChecks(properties);
        dialog.getDialog().pack();
        dialog.getDialog().setSize(size);
        return dialog;
    }

    /**
     * Opens the CSV import dialog.
     */
    public void openDialog(CsvImportDialog dialog) {
        dialog.getDialog().setLocationRelativeTo(frame);
        dialog.openDialog();
    }

    /**
     * Creates the CSV importer from the user input of the CSV import dialog.
     */
    public CsvImporter createValue(CsvImportDialog dialog) throws Exception {
        if (dialog.getStatus() == SimpleDialog.Status.CANCELED) {
            return null;
        }
        CsvImportProperties properties = dialog.getProperties();
        CsvImporter importer = csvImporterFactory.create(properties);
        this.savedProperties = importer.getProperties();
        return importer;
    }

    /**
     * Sets the size of the CSV import dialog.
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

    /**
     * Sets the parent frame for the CSV import dialog.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Sets the previous set file of the CSV import dialog.
     */
    public void setPreviousFile(URI previousFile) {
        this.previousFile = previousFile;
    }

    /**
     * Sets the saved properties of the CSV import dialog.
     */
    public void setSavedProperties(CsvImportProperties savedProperties) {
        this.savedProperties = savedProperties;
    }

    private DefaultCsvImportProperties setupSavedProperties(
            DefaultCsvImportProperties properties,
            CsvImportProperties savedProperties) {
        properties.setFile(previousFile);
        if (savedProperties != null) {
            URI file = savedProperties.getFile();
            if (file != null) {
                properties.setFile(new File(file).getParentFile().toURI());
            }
            properties.setCharset(savedProperties.getCharset());
            properties.setEndOfLineSymbols(savedProperties
                    .getEndOfLineSymbols());
            properties.setLocale(savedProperties.getLocale());
            properties.setNumCols(savedProperties.getNumCols());
            properties.setQuote(savedProperties.getQuote());
            properties.setSeparator(savedProperties.getSeparator());
            properties.setStartRow(savedProperties.getStartRow());
        }
        return properties;
    }

}
