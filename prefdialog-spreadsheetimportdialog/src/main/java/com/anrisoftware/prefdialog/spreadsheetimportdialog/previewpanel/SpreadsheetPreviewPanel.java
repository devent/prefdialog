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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportException;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporter;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.google.inject.Injector;

/**
 * Preview panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class SpreadsheetPreviewPanel {

    @Inject
    private Injector injector;

    @Inject
    private SpreadsheetPreviewPanelLogger log;

    private SpreadsheetImporterFactory importerFactory;

    @Inject
    private UiPreviewPanel panel;

    @Inject
    private PreviewDataTableModel previewDataTableModel;

    /**
     * Creates the panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return this {@link SpreadsheetPreviewPanel}.
     */
    @OnAwt
    public SpreadsheetPreviewPanel createPanel(
            SpreadsheetImporterFactory importerFactory) {
        return createPanel(injector, importerFactory);
    }

    /**
     * Creates the panel from the parent Guice injector.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param parent
     *            the parent {@link Injector}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return this {@link SpreadsheetPreviewPanel}.
     */
    @OnAwt
    public SpreadsheetPreviewPanel createPanel(Injector parent,
            SpreadsheetImporterFactory importerFactory) {
        this.importerFactory = importerFactory;
        setupPanel();
        setupActions();
        return this;
    }

    /**
     * Returns the created preview panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Component}.
     */
    @OnAwt
    public Component getAWTComponent() {
        return panel;
    }

    /**
     * Sets the spreadsheet importer properties.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     */
    @OnAwt
    public void setProperties(SpreadsheetImportProperties properties) {
        updateData(properties);
    }

    /**
     * Sets the focus on the preview panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     */
    @OnAwt
    public void requestFocus() {
    }

    /**
     * Restores the input of the panel to default values.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @throws PropertyVetoException
     *             if the old user input is not valid.
     */
    public void retoreInput() throws PropertyVetoException {
    }

    private void setupActions() {
    }

    private void setupPanel() {
        panel.dataTable.setModel(previewDataTableModel);
    }

    private void updateData(SpreadsheetImportProperties properties) {
        if (properties == null) {
            resetDataPreview();
            return;
        }
        URI uri = properties.getFile();
        if (uri == null) {
            resetDataPreview();
            return;
        }
        File file = new File(uri);
        if (!file.isFile()) {
            resetDataPreview();
            return;
        }
        try {
            SpreadsheetImporter importer = importerFactory.create(properties);
            previewDataTableModel.setImporter(importer);
        } catch (SpreadsheetImportException e) {
            log.errorRead(this, e);
        }
    }

    private void resetDataPreview() {
        try {
            previewDataTableModel.setImporter(null);
        } catch (SpreadsheetImportException e) {
            log.errorRead(this, e);
        }
    }

}
