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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.importpaneldock;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock.AbstractEditorDockWindow;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanel;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelFactory;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelProperties;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;

/**
 * Dock containing the spreadsheet import panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@SuppressWarnings("serial")
public class SpreadsheetImportPanelDock extends AbstractEditorDockWindow {

    /**
     * The ID name of the import panel dock.
     */
    public static final String ID = "spreadsheetImportPanelDock";

    @Inject
    private transient SpreadsheetImportPanelFactory panelFactory;

    private JPanel panel;

    private SpreadsheetImportPanel importPanel;

    private Texts texts;

    private Locale locale;

    /**
     * Sets the spreadsheet import panel factory.
     *
     * @param panelFactory
     *            the {@link SpreadsheetImportPanelFactory}.
     */
    public void setPanelFactory(SpreadsheetImportPanelFactory panelFactory) {
        this.panelFactory = panelFactory;
    }

    /**
     * Injects the texts resources factory.
     *
     * @param factory
     *            the {@link TextsFactory}.
     */
    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(SpreadsheetImportPanelDock.class
                .getSimpleName());
    }

    /**
     * Sets the localized texts resources. The texts must contain the following
     * resources:
     *
     * <ul>
     * <li>{@code "import_panel_dock_title"}</li>
     * </ul>
     *
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param texts
     *            the {@link Texts}.
     */
    @OnAwt
    public void setTexts(Texts texts) {
        this.texts = texts;
        updateTexts();
    }

    /**
     * Sets the locale for the dock.
     *
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
        updateTexts();
    }

    /**
     * Sets the CSV import properties.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param injector
     *            the parent {@link Injector}.
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return this {@link SpreadsheetImportPanelDock}.
     */
    @OnAwt
    public SpreadsheetImportPanelDock createPanel(Injector injector,
            SpreadsheetImportProperties properties,
            SpreadsheetImporterFactory importerFactory) {
        createImportPanel(injector, properties, importerFactory);
        updateTexts();
        return this;
    }

    /**
     * Returns the CSV import panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetImportPanel}.
     */
    @OnAwt
    public SpreadsheetImportPanel getImportPanel() {
        return importPanel;
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
    public void restoreInput() throws PropertyVetoException {
        importPanel.retoreInput();
    }

    /**
     * Returns the spreadsheet panel properties.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetImportProperties}.
     */
    @OnAwt
    public SpreadsheetPanelProperties getProperties() {
        return importPanel.getProperties();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public Component getComponent() {
        return panel;
    }

    @Override
    public DockPosition getPosition() {
        return DockPosition.EAST;
    }

    @Override
    public boolean isCloseable() {
        return false;
    }

    @Override
    public boolean isExternalizable() {
        return false;
    }

    @Override
    public boolean isMaximizable() {
        return true;
    }

    @Override
    public boolean isMinimizable() {
        return false;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(VetoableChangeListener)
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        importPanel.addVetoableChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#removeVetoableChangeListener(VetoableChangeListener)
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        importPanel.removeVetoableChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanel.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see SpreadsheetImportPanel#removeVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanel.removeVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see SpreadsheetImportPanel#addPropertyChangeListener(PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        importPanel.addPropertyChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#removePropertyChangeListener(PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        importPanel.removePropertyChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        importPanel.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @see SpreadsheetImportPanel#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        importPanel.removePropertyChangeListener(propertyName, listener);
    }

    private void createImportPanel(Injector injector,
            SpreadsheetImportProperties properties,
            SpreadsheetImporterFactory importerFactory) {
        this.panel = new JPanel();
        panel.setLocale(locale);
        importPanel = panelFactory.create(panel, properties, importerFactory);
        importPanel.setLocale(locale);
        importPanel.createPanel(injector);
    }

    private static final String IMPORT_PANEL_DOCK_TITLE_NAME = "import_panel_dock_title";

    private void updateTexts() {
        if (texts == null) {
            return;
        }
        if (locale == null) {
            return;
        }
        TextResource titleResource;
        titleResource = texts.getResource(IMPORT_PANEL_DOCK_TITLE_NAME, locale);
        String title = titleResource.getText();
        setTitle(title);
    }

}
