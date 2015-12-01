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

import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Dimension;
import java.awt.Window;
import java.beans.VetoableChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.inject.Inject;
import javax.swing.JDialog;

import com.anrisoftware.globalpom.spreadsheetimport.DefaultSpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.AbstractCreateDialogWorker;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutException;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanel;
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

    private final List<VetoableChangeListener> vetoableChangeListeners;

    private final Map<String, List<VetoableChangeListener>> namedVetoableChangeListeners;

    @Inject
    private SpreadsheetImportDialogWorkerLogger log;

    @Inject
    private SpreadsheetImportDialogFactory spreadsheetImportDialogFactory;

    @Inject
    private DefaultSpreadsheetImportProperties properties;

    @Inject
    private Injector parent;

    private Dimension size;

    private Window parentWindow;

    private URI previousFile;

    private SpreadsheetImportProperties savedProperties;

    private SpreadsheetImporterFactory importerFactory;

    private SoftReference<SpreadsheetImportDialog> importDialog;

    private byte[] currentLayout;

    SpreadsheetImportDialogWorker() {
        this.vetoableChangeListeners = new ArrayList<VetoableChangeListener>();
        this.namedVetoableChangeListeners = new HashMap<String, List<VetoableChangeListener>>();
    }

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
     * @param parentWindow
     *            the parent {@link Window}.
     */
    public void setParentWindow(Window parentWindow) {
        this.parentWindow = parentWindow;
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
     * @return the {@link SpreadsheetImportDialog} or {@code null} if the dialog
     *         was not created.
     */
    public synchronized SpreadsheetImportDialog getImportDialog() {
        return importDialog.get();
    }

    /**
     * Sets the current layout of the dialog.
     */
    public synchronized void setCurrentLayout(byte[] currentLayout) {
        notNull(importDialog, "importDialog=null");
        SpreadsheetImportDialog dialog = importDialog.get();
        notNull(dialog, "dialog=null");
        this.currentLayout = currentLayout;
        try {
            ByteArrayInputStream stream;
            stream = new ByteArrayInputStream(currentLayout);
            GZIPInputStream zstream = new GZIPInputStream(stream);
            dialog.loadLayout("default", zstream);
        } catch (LayoutException e) {
            log.errorLoadLayout(dialog, e);
        } catch (IOException e) {
            log.errorLoadLayout(dialog, e);
        }
    }

    /**
     * Returns the current layout of the dialog.
     */
    public synchronized byte[] getCurrentLayout() {
        notNull(importDialog, "importDialog=null");
        SpreadsheetImportDialog dialog = importDialog.get();
        notNull(dialog, "dialog=null");
        try {
            ByteArrayOutputStream stream;
            stream = new ByteArrayOutputStream(1024);
            GZIPOutputStream zstream = new GZIPOutputStream(stream);
            dialog.saveLayout("default", zstream);
            currentLayout = stream.toByteArray();
        } catch (LayoutException e) {
            log.errorSaveLayout(dialog, e);
        } catch (IOException e) {
            log.errorSaveLayout(dialog, e);
        }
        return currentLayout;
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(VetoableChangeListener)
     * @see SimpleDialog#addVetoableChangeListener(VetoableChangeListener)
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeListeners.add(listener);
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     * @see SimpleDialog#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        List<VetoableChangeListener> list = namedVetoableChangeListeners
                .get(propertyName);
        if (list == null) {
            list = new ArrayList<VetoableChangeListener>();
            namedVetoableChangeListeners.put(propertyName, list);
        }
        list.add(listener);
    }

    @Override
    protected boolean needRecreateDialog() {
        return super.needRecreateDialog() || importDialog == null
                || importDialog.get() == null;
    }

    @Override
    protected JDialog createDialog() {
        JDialog jdialog = new JDialog(parentWindow, APPLICATION_MODAL);
        jdialog.setLocale(getLocale());
        SpreadsheetImportDialog importDialog;
        importDialog = spreadsheetImportDialogFactory.create(savedProperties);
        importDialog.setParentInjector(parent);
        importDialog.setDialog(jdialog);
        importDialog.createDialog(parentWindow, importerFactory);
        setupSavedProperties(properties, savedProperties);
        importDialog.setPropertiesNoChecks(properties);
        jdialog.pack();
        jdialog.setSize(size);
        jdialog.setTitle(getDialogTitleFromResource());
        jdialog.setLocationRelativeTo(parentWindow);
        insertListeners(importDialog);
        this.importDialog = new SoftReference<SpreadsheetImportDialog>(
                importDialog);
        return jdialog;
    }

    private void insertListeners(SpreadsheetImportDialog importDialog) {
        for (VetoableChangeListener l : vetoableChangeListeners) {
            importDialog.addVetoableChangeListener(l);
        }
        for (Map.Entry<String, List<VetoableChangeListener>> entry : namedVetoableChangeListeners
                .entrySet()) {
            for (VetoableChangeListener l : entry.getValue()) {
                importDialog.addVetoableChangeListener(entry.getKey(), l);
            }
        }
    }

    private DefaultSpreadsheetImportProperties setupSavedProperties(
            DefaultSpreadsheetImportProperties properties,
            SpreadsheetImportProperties savedProperties) {
        if (previousFile != null) {
            properties.setFile(previousFile);
        }
        if (savedProperties != null) {
            URI file = savedProperties.getFile();
            if (file != null && properties.getFile() == null) {
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
