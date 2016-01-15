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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel;

import static com.anrisoftware.prefdialog.fields.FieldComponent.VALUE_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener.lockedPropertyChangeListener;
import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.anrisoftware.globalpom.spreadsheetimport.DefaultSpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.DefaultSpreadsheetImportPropertiesFactory;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporter;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.filechooser.FileChooserField;
import com.anrisoftware.prefdialog.fields.spinner.SpinnerField;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelProperties;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesFactory;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Spreadsheet import panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class SpreadsheetImportPanel {

    private static final String PANEL_BEAN = "importPanel";

    private final JPanel container;

    private final SpreadsheetPanelProperties properties;

    private final LockedPropertyChangeListener valueListener;

    private final SpreadsheetImporterFactory importerFactory;

    @Inject
    private Injector injector;

    @Inject
    private SpreadsheetImportPanelLogger log;

    @Inject
    private VerticalPreferencesPanelFieldProvider fieldService;

    @Inject
    private DefaultSpreadsheetImportPropertiesFactory spreadsheetImportPropertiesFactory;

    private Texts texts;

    private VerticalPreferencesPanelField propertiesPanel;

    private SpinnerField sheetNumberField;

    private Locale locale;

    private FileChooserField fileField;

    /**
     * @see SpreadsheetImportPanelFactory#create(JPanel,
     *      SpreadsheetImportProperties, SpreadsheetImporterFactory)
     */
    @Inject
    SpreadsheetImportPanel(@Assisted JPanel container,
            @Assisted SpreadsheetImportProperties properties,
            @Assisted SpreadsheetImporterFactory importerFactory,
            SpreadsheetPanelPropertiesFactory propertiesFactory) {
        this.container = container;
        this.properties = propertiesFactory.create(properties);
        this.importerFactory = importerFactory;
        this.locale = Locale.getDefault();
        this.valueListener = lockedPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                valueListener.lock();
                updateSheetsCount();
                valueListener.unlock();
            }
        });
    }

    /**
     * Sets the texts resources factory.
     *
     * @param factory
     *            the {@link TextsFactory}.
     */
    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(SpreadsheetImportPanel.class
                .getSimpleName());
    }

    /**
     * Sets the locale for the dock.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param locale
     *            the {@link Locale}.
     */
    @OnAwt
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Creates the panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return this {@link SpreadsheetImportPanel}.
     */
    @OnAwt
    public SpreadsheetImportPanel createPanel() {
        return createPanel(injector);
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
     * @return this {@link SpreadsheetImportPanel}.
     */
    @OnAwt
    public SpreadsheetImportPanel createPanel(Injector parent) {
        createPropertiesPanel(parent);
        setupPanel();
        setupActions();
        updateFile();
        updateSheetsCount();
        return this;
    }

    /**
     * Returns the import panel component.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Component}.
     */
    @OnAwt
    public Component getAWTComponent() {
        return container;
    }

    /**
     * Returns the Spreadsheet properties of the panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetPanelProperties}.
     */
    @OnAwt
    public SpreadsheetPanelProperties getProperties() {
        return properties;
    }

    /**
     * Returns the created vertical preferences panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link VerticalPreferencesPanelField}.
     */
    @OnAwt
    public VerticalPreferencesPanelField getPanel() {
        return propertiesPanel;
    }

    /**
     * Sets the focus on the CSV import panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     */
    @OnAwt
    public void requestFocus() {
        FieldComponent<Component> file = propertiesPanel.findField("file");
        file.requestFocus();
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
    @OnAwt
    public void retoreInput() throws PropertyVetoException {
        propertiesPanel.restoreInput();
    }

    /**
     * @see FieldComponent#addVetoableChangeListener(VetoableChangeListener)
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        propertiesPanel.addVetoableChangeListener(listener);
    }

    /**
     * @see FieldComponent#removeVetoableChangeListener(VetoableChangeListener)
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        propertiesPanel.removeVetoableChangeListener(listener);
    }

    /**
     * @see FieldComponent#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        propertiesPanel.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see FieldComponent#removeVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        propertiesPanel.removeVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see FieldComponent#addPropertyChangeListener(PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertiesPanel.addPropertyChangeListener(listener);
    }

    /**
     * @see FieldComponent#removePropertyChangeListener(PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertiesPanel.removePropertyChangeListener(listener);
    }

    /**
     * @see FieldComponent#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertiesPanel.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @see FieldComponent#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertiesPanel.removePropertyChangeListener(propertyName, listener);
    }

    private void createPropertiesPanel(Injector parent) {
        VerticalPreferencesPanelField panel;
        panel = (VerticalPreferencesPanelField) fieldService.get()
                .getFactory(parent).create(properties, PANEL_BEAN);
        panel.createPanel(parent);
        panel.addPropertyChangeListener(VALUE_PROPERTY, valueListener);
        panel.setLocale(locale);
        this.propertiesPanel = panel;
    }

    private void setupActions() {
    }

    private void setupPanel() {
        propertiesPanel.setTexts(texts);
        container.setLayout(new BorderLayout());
        container.add(propertiesPanel.getAWTComponent(), CENTER);
        sheetNumberField = propertiesPanel.findField("sheetNumber");
        fileField = propertiesPanel.findField("file");
    }

    private void updateFile() {
        URI uri = properties.getFile();
        if (uri == null) {
            return;
        }
        try {
            File file = new File(uri);
            fileField.setValue(file);
            properties.getFileProperties().setFile(file);
            properties.getFileProperties().getFileModel().setFile(file);
        } catch (PropertyVetoException e) {
        }
    }

    private void updateSheetsCount() {
        URI uri = properties.getFile();
        if (uri == null) {
            setSheetsCount(0);
            return;
        }
        File file = new File(uri);
        if (!file.isFile()) {
            setSheetsCount(0);
            return;
        }
        try {
            DefaultSpreadsheetImportProperties p;
            p = spreadsheetImportPropertiesFactory.create(properties);
            p.setSheetNumber(0);
            SpreadsheetImporter importer = importerFactory.create(p);
            readSheetsCount(importer);
        } catch (IOException e) {
            log.errorRead(this, e);
        }
    }

    private void readSheetsCount(SpreadsheetImporter importer)
            throws IOException {
        importer.open();
        setSheetsCount(importer.getSheetsCount());
        importer.close();
    }

    private void setSheetsCount(int size) {
        if (sheetNumberField == null) {
            return;
        }
        SpinnerModel model = sheetNumberField.getModel();
        if (model instanceof SpinnerNumberModel) {
            SpinnerNumberModel nmodel = (SpinnerNumberModel) model;
            nmodel.setMaximum(size - 1);
            if (size == 0) {
                nmodel.setMinimum(0);
                nmodel.setValue(0);
            } else {
                nmodel.setMinimum(0);
                nmodel.setValue(0);
            }
        }
    }

}
