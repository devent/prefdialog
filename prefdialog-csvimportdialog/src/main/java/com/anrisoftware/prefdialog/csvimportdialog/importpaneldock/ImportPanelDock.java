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
package com.anrisoftware.prefdialog.csvimportdialog.importpaneldock;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.dataimport.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock.AbstractEditorDockWindow;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;

/**
 * Dock containing the CSV import panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ImportPanelDock extends AbstractEditorDockWindow {

    public static final String ID = "importPanelDock";

    @Inject
    private transient CsvImportPanelFactory panelFactory;

    private JPanel panel;

    private CsvImportPanel importPanel;

    private Texts texts;

    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(ImportPanelDock.class.getSimpleName());
    }

    /**
     * Sets the CSV import properties.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param injector
     *            the parent {@link Injector}.
     *
     * @param properties
     *            the {@link CsvImportProperties}.
     *
     * @return this {@link ImportPanelDock}.
     */
    @OnAwt
    public ImportPanelDock createPanel(Injector injector,
            CsvImportProperties properties) {
        this.panel = new JPanel();
        setTexts(texts);
        importPanel = panelFactory.create(panel, properties);
        importPanel.createPanel(injector);
        return this;
    }

    /**
     * Returns the CSV import panel.
     *
     * @return the {@link CsvImportPanel}.
     */
    public CsvImportPanel getImportPanel() {
        return importPanel;
    }

    /**
     * Restores the input of the panel to default values.
     *
     * @throws PropertyVetoException
     *             if the old user input is not valid.
     */
    public void restoreInput() throws PropertyVetoException {
        importPanel.retoreInput();
    }

    public CsvImportProperties getProperties() {
        return importPanel.getProperties();
    }

    /**
     * Sets the localized texts resources. The texts must contain the following
     * resources:
     *
     * <ul>
     * <li>{@code "import_panel_dock_title"}</li>
     * </ul>
     *
     * @param texts
     *            the {@link Texts}.
     */
    @OnAwt
    public void setTexts(Texts texts) {
        setTitle(texts.getResource("import_panel_dock_title").getText());
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
     * @see CsvImportPanel#addVetoableChangeListener(VetoableChangeListener)
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        importPanel.addVetoableChangeListener(listener);
    }

    /**
     * @see CsvImportPanel#removeVetoableChangeListener(VetoableChangeListener)
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        importPanel.removeVetoableChangeListener(listener);
    }

    /**
     * @see CsvImportPanel#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanel.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see CsvImportPanel#removeVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanel.removeVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see CsvImportPanel#addPropertyChangeListener(PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        importPanel.addPropertyChangeListener(listener);
    }

    /**
     * @see CsvImportPanel#removePropertyChangeListener(PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        importPanel.removePropertyChangeListener(listener);
    }

    /**
     * @see CsvImportPanel#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        importPanel.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @see CsvImportPanel#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     */
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        importPanel.removePropertyChangeListener(propertyName, listener);
    }

}
