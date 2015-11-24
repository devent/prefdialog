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
import java.util.Locale;

import javax.inject.Inject;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock.AbstractViewDockWindow;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.SpreadsheetPreviewPanel;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.SpreadsheetPreviewPanelFactory;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;

/**
 * Dock containing the preview panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@SuppressWarnings("serial")
public class SpreadsheetPreviewPanelDock extends AbstractViewDockWindow {

    /**
     * The ID name of the preview panel dock.
     */
    public static final String ID = "spreadsheetPreviewPanelDock";

    @Inject
    private transient SpreadsheetPreviewPanelFactory panelFactory;

    private SpreadsheetPreviewPanel previewPanel;

    private Texts texts;

    private Locale locale;

    /**
     * Injects the texts resources factory.
     *
     * @param factory
     *            the {@link TextsFactory}.
     */
    @Inject
    public void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(SpreadsheetPreviewPanelDock.class
                .getSimpleName());
    }

    /**
     * Sets the localized texts resources. The texts must contain the following
     * resources:
     *
     * <ul>
     * <li>{@code "preview_panel_dock_title"}</li>
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
     * Creates the panel with the spreadsheet import properties.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param injector
     *            the parent {@link Injector}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return this {@link SpreadsheetPreviewPanelDock}.
     */
    @OnAwt
    public SpreadsheetPreviewPanelDock createPanel(Injector injector,
            SpreadsheetImporterFactory importerFactory) {
        previewPanel = panelFactory.create();
        previewPanel.createPanel(injector, importerFactory);
        updateTexts();
        return this;
    }

    /**
     * Sets the CSV importer properties.
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
        previewPanel.setProperties(properties);
    }

    /**
     * Returns the preview panel.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetPreviewPanel}.
     */
    @OnAwt
    public SpreadsheetPreviewPanel getImportPanel() {
        return previewPanel;
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

    private static final String PREVIEW_PANEL_DOCK_TITLE_NAME = "preview_panel_dock_title";

    private void updateTexts() {
        if (texts == null) {
            return;
        }
        if (locale == null) {
            return;
        }
        TextResource titleResource;
        titleResource = texts
                .getResource(PREVIEW_PANEL_DOCK_TITLE_NAME, locale);
        String title = titleResource.getText();
        setTitle(title);
    }

}
