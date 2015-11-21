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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpaneldock;

import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.inject.Inject;

import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock.AbstractViewDockWindow;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.PreviewPanel;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.PreviewPanelFactory;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpaneldock.PreviewPanelDock;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;

/**
 * Dock containing the preview panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
@SuppressWarnings("serial")
public class PreviewPanelDock extends AbstractViewDockWindow {

    public static final String ID = "previewPanelDock";

    @Inject
    private transient PreviewPanelFactory panelFactory;

    private PreviewPanel previewPanel;

    private Texts texts;

    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(PreviewPanelDock.class.getSimpleName());
    }

    /**
     * Creates the panel with the CSV import properties.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param injector
     *            the parent {@link Injector}.
     *
     * @return this {@link PreviewPanelDock}.
     */
    @OnAwt
    public PreviewPanelDock createPanel(Injector injector) {
        setTexts(texts);
        previewPanel = panelFactory.create();
        previewPanel.createPanel(injector);
        return this;
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
        previewPanel.setProperties(properties);
    }

    /**
     * Sets the localized texts resources. The texts must contain the following
     * resources:
     *
     * <ul>
     * <li>{@code "preview_panel_dock_title"}</li>
     * </ul>
     *
     * @param texts
     *            the {@link Texts}.
     */
    @OnAwt
    public void setTexts(Texts texts) {
        setTitle(texts.getResource("preview_panel_dock_title").getText());
    }

    /**
     * Returns the preview panel.
     *
     * @return the {@link PreviewPanel}.
     */
    public PreviewPanel getImportPanel() {
        return previewPanel;
    }

    /**
     * Restores the input of the panel to default values.
     *
     * @throws PropertyVetoException
     *             if the old user input is not valid.
     */
    public void restoreInput() throws PropertyVetoException {
        previewPanel.retoreInput();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public Component getComponent() {
        return previewPanel.getAWTComponent();
    }

    @Override
    public DockPosition getPosition() {
        return DockPosition.WEST;
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
        return true;
    }

    @Override
    public boolean isStackable() {
        return true;
    }

}
