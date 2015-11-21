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
package com.anrisoftware.prefdialog.csvimportdialog.previewpanel;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;

import com.anrisoftware.globalpom.csvimport.CsvImportException;
import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.globalpom.csvimport.CsvImporter;
import com.anrisoftware.globalpom.csvimport.CsvImporterFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.google.inject.Injector;

/**
 * Preview panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
public class PreviewPanel {

    @Inject
    private Injector injector;

    @Inject
    private PreviewPanelLogger log;

    @Inject
    private CsvImporterFactory importerFactory;

    @Inject
    private UiPreviewPanel panel;

    @Inject
    private PreviewDataTableModel previewDataTableModel;

    /**
     * @see #createPanel(Injector)
     */
    @OnAwt
    public PreviewPanel createPanel() {
        return createPanel(injector);
    }

    /**
     * Creates the panel from the parent Guice injector.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param parent
     *            the parent {@link Injector}.
     *
     * @return this {@link PreviewPanel}.
     */
    @OnAwt
    public PreviewPanel createPanel(Injector parent) {
        setupPanel();
        setupActions();
        return this;
    }

    private void setupActions() {
    }

    private void setupPanel() {
        panel.getDataTable().setModel(previewDataTableModel);
    }

    /**
     * Returns the created preview panel.
     *
     * @return the {@link Component}.
     */
    public Component getAWTComponent() {
        return panel;
    }

    /**
     * Sets the CSV importer properties.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param properties
     *            the {@link CsvImportProperties}.
     */
    @OnAwt
    public void setProperties(CsvImportProperties properties) {
        updateData(properties);
    }

    /**
     * Sets the focus on the preview panel.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     */
    @OnAwt
    public void requestFocus() {
    }

    /**
     * Restores the input of the panel to default values.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @throws PropertyVetoException
     *             if the old user input is not valid.
     */
    public void retoreInput() throws PropertyVetoException {
    }

    private void updateData(CsvImportProperties properties) {
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
            CsvImporter importer = importerFactory.create(properties);
            previewDataTableModel.setImporter(importer);
        } catch (CsvImportException e) {
            log.errorRead(this, e);
        }
    }

    private void resetDataPreview() {
        try {
            previewDataTableModel.setImporter(null);
        } catch (CsvImportException e) {
            log.errorRead(this, e);
        }
    }

}
