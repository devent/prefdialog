/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

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
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.csvimport.CsvImportException;
import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.globalpom.csvimport.CsvImporter;
import com.anrisoftware.globalpom.csvimport.CsvImporterFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.UseCustomQuoteAction;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties.UseCustomSeparatorAction;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * CSV file import panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvImportPanel {

    private static final String PANEL_BEAN = "importPanel";

    private final JPanel container;

    private final CsvPanelProperties properties;

    private final LockedPropertyChangeListener valueListener;

    @Inject
    private Injector injector;

    @Inject
    private CsvImportPanelLogger log;

    @Inject
    private VerticalPreferencesPanelFieldProvider fieldService;

    @Inject
    private CsvImporterFactory importerFactory;

    private Texts texts;

    private VerticalPreferencesPanelField propertiesPanel;

    private FieldComponent<Component> numberColumnsField;

    /**
     * @see CsvImportPanelFactory#create(JPanel, CsvImportProperties)
     */
    @Inject
    CsvImportPanel(CsvPanelPropertiesFactory propertiesFactory,
            @Assisted JPanel container, @Assisted CsvImportProperties properties) {
        this.container = container;
        this.properties = propertiesFactory.create(properties);
        this.valueListener = lockedPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                valueListener.lock();
                updateColumns();
                valueListener.unlock();
            }
        });
    }

    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(CsvImportPanel.class.getSimpleName());
    }

    /**
     * @see #createPanel(Injector)
     */
    @OnAwt
    public CsvImportPanel createPanel() {
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
     * @return this {@link CsvImportPanel}.
     */
    @OnAwt
    public CsvImportPanel createPanel(Injector parent) {
        this.propertiesPanel = (VerticalPreferencesPanelField) fieldService
                .get().getFactory(parent).create(properties, PANEL_BEAN);
        propertiesPanel.createPanel(parent);
        propertiesPanel
                .addPropertyChangeListener(VALUE_PROPERTY, valueListener);
        setupPanel();
        setupActions();
        return this;
    }

    private void setupActions() {
        UseCustomSeparatorAction useCustomSeparatorAction = properties
                .getSeparatorProperties().getUseCustomSeparatorAction();
        useCustomSeparatorAction.setCustomSeparatorCharField(propertiesPanel
                .findField("customSeparatorChar"));
        useCustomSeparatorAction.setSeparatorCharField(propertiesPanel
                .findField("separatorChar"));
        UseCustomQuoteAction useCustomQuoteAction = properties
                .getAdvancedProperties().getUseCustomQuoteAction();
        useCustomQuoteAction.setQuoteField(propertiesPanel
                .findField("quoteChar"));
        useCustomQuoteAction.setCustomQuoteField(propertiesPanel
                .findField("customQuoteChar"));
    }

    private void setupPanel() {
        propertiesPanel.setTexts(texts);
        container.setLayout(new BorderLayout());
        container.add(propertiesPanel.getAWTComponent(), CENTER);
        numberColumnsField = propertiesPanel.findField("numberColumns");
    }

    public Component getAWTComponent() {
        return container;
    }

    /**
     * Returns the CSV properties of the panel.
     *
     * @return the {@link CsvImportProperties}.
     */
    public CsvImportProperties getProperties() {
        return properties;
    }

    /**
     * Returns the created vertical preferences panel.
     *
     * @return the {@link VerticalPreferencesPanelField}.
     */
    public VerticalPreferencesPanelField getPanel() {
        return propertiesPanel;
    }

    /**
     * Sets the focus on the CSV import panel.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
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
     * <p>
     * Should be called in the AWT thread.
     *
     * @throws PropertyVetoException
     *             if the old user input is not valid.
     */
    public void retoreInput() throws PropertyVetoException {
        propertiesPanel.restoreInput();
    }

    private void updateColumns() {
        URI uri = properties.getFile();
        if (uri == null) {
            setNumberColumns(0);
            return;
        }
        File file = new File(uri);
        if (!file.isFile()) {
            setNumberColumns(0);
            return;
        }
        try {
            CsvImporter importer = importerFactory.create(properties);
            readNumberColumns(importer);
        } catch (CsvImportException e) {
            log.errorRead(this, e);
        }
    }

    private void readNumberColumns(CsvImporter importer)
            throws CsvImportException {
        List<String> values = importer.call().getValues();
        if (values != null) {
            setNumberColumns(values.size());
        }
    }

    private void setNumberColumns(int size) {
        if (numberColumnsField == null) {
            return;
        }
        try {
            numberColumnsField.setValue(size);
        } catch (PropertyVetoException e) {
        }
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

}
