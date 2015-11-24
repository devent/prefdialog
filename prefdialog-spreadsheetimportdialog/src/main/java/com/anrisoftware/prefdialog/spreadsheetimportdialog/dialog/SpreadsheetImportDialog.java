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

import static com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogModule.getFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanel;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpaneldock.SpreadsheetImportPanelDock;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelProperties;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpaneldock.SpreadsheetPreviewPanelDock;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Dialog to import spreadsheet data.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class SpreadsheetImportDialog extends SimpleDialog {

    /**
     * Decorates the dialog with the spreadsheet import panel and dialog
     * actions.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     *
     * @param dialog
     *            the {@link JDialog}.
     *
     * @param frame
     *            the {@link JFrame} owner or {@code null}.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return the {@link SpreadsheetImportDialog}.
     */
    @OnAwt
    public static SpreadsheetImportDialog decorateSpreadsheetImportDialog(
            SpreadsheetImportProperties properties, JDialog dialog,
            JFrame frame, Locale locale,
            SpreadsheetImporterFactory importerFactory) {
        return decorate(properties, dialog, frame, locale, importerFactory);
    }

    /**
     * Decorates the dialog with the spreadsheet import panel and dialog
     * actions.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     *
     * @param dialog
     *            the {@link JDialog}.
     *
     * @param frame
     *            the {@link JFrame} owner or {@code null}.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return the {@link SpreadsheetImportDialog}.
     */
    @OnAwt
    public static SpreadsheetImportDialog decorate(
            SpreadsheetImportProperties properties, JDialog dialog,
            JFrame frame, Locale locale,
            SpreadsheetImporterFactory importerFactory) {
        SpreadsheetImportDialog importDialog;
        importDialog = getFactory().create(properties);
        importDialog.setDialog(dialog);
        importDialog.setLocale(locale);
        importDialog.createDialog(frame, importerFactory);
        return importDialog;
    }

    private final SpreadsheetImportProperties properties;

    private final PropertyChangeListener propertyChangeListener;

    private final Dock dock;

    @Inject
    private SpreadsheetImportPanelDock importPanelDock;

    @Inject
    private SpreadsheetPreviewPanelDock previewPanelDock;

    @Inject
    private Injector parent;

    @Inject
    private ValidListener validListener;

    @Inject
    private PropertiesWorkerFactory propertiesWorkerFactory;

    private Texts texts;

    /**
     * @see SpreadsheetImportDialogFactory#create(SpreadsheetImportProperties)
     */
    @Inject
    SpreadsheetImportDialog(DockFactory dockFactory,
            @Assisted SpreadsheetImportProperties properties) {
        this.dock = dockFactory.create();
        this.properties = properties;
        this.propertyChangeListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                previewPanelDock.setProperties(importPanelDock.getProperties());
            }
        };
    }

    /**
     * Sets the texts resources factory.
     *
     * @param factory
     *            the {@link TextsFactory}.
     */
    @Inject
    public void setSpreadsheetImportDialogTextsFactory(TextsFactory factory) {
        this.texts = factory.create(SpreadsheetImportDialog.class
                .getSimpleName());
    }

    /**
     * Sets the parent dependency modules.
     *
     * @param parent
     *            the parent {@link Inject}.
     */
    public void setParent(Injector parent) {
        this.parent = parent;
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
    @Override
    @OnAwt
    public void setLocale(Locale locale) {
        super.setLocale(locale);
    }

    /**
     * Created the spreadsheet import dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param frame
     *            the parent {@link JFrame} owner or {@code null}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return this {@link SpreadsheetImportDialog}.
     */
    @OnAwt
    public SpreadsheetImportDialog createDialog(JFrame frame,
            SpreadsheetImporterFactory importerFactory) {
        createDialog0(frame, importerFactory);
        return (SpreadsheetImportDialog) super.createDialog();
    }

    /**
     * Returns the dock.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Dock}.
     */
    @OnAwt
    public Dock getDock() {
        return dock;
    }

    /**
     * Sets the properties to the dialog fields.
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     *
     * @throws PropertyVetoException
     *             if one of the values of the properties are vetoed.
     */
    @OnAwt
    public void setProperties(SpreadsheetImportProperties properties)
            throws PropertyVetoException {
        FieldComponent<?> field = getImportPanelField();
        SpreadsheetPanelProperties p = getProperties();
        propertiesWorkerFactory.create(field, p).setProperties(properties);
        previewPanelDock.setProperties(properties);
    }

    /**
     * Sets the properties to the dialog fields. if one of the values of the
     * properties is vetoed the value is skipped.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param properties
     *            the {@link CsvImportProperties}.
     */
    @OnAwt
    public void setPropertiesNoChecks(SpreadsheetImportProperties properties) {
        FieldComponent<?> field = getImportPanelField();
        SpreadsheetPanelProperties p = getProperties();
        propertiesWorkerFactory.create(field, p).setPropertiesNoChecks(
                properties);
        previewPanelDock.setProperties(properties);
    }

    /**
     * Returns the spreadsheet import properties.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetPanelProperties}.
     */
    @OnAwt
    public SpreadsheetPanelProperties getProperties() {
        return importPanelDock.getImportPanel().getProperties();
    }

    /**
     * Returns the import panel dock.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link SpreadsheetImportPanelDock}.
     */
    @OnAwt
    public SpreadsheetImportPanelDock getImportPanelDock() {
        return importPanelDock;
    }

    /**
     * Returns the CSV import panel field.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link FieldComponent}.
     */
    @OnAwt
    public FieldComponent<JPanel> getImportPanelField() {
        return importPanelDock.getImportPanel().getPanel();
    }

    /**
     * Saves the current layout under the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the perspective.
     *
     * @param file
     *            the {@link File} file where to save the layout.
     *
     * @throws IOException
     *             if there was I/O error saving the layout.
     */
    public void saveLayout(String name, File file) throws IOException {
        dock.saveLayout(name, file);
    }

    /**
     * Saves the current layout under the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param stream
     *            the {@link OutputStream} stream where to save the layout.
     *
     * @throws IOException
     *             if there was I/O error saving the layout.
     */
    public void saveLayout(String name, OutputStream stream) throws IOException {
        dock.saveLayout(name, stream);
    }

    /**
     * Loads the previously saved layout with the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param file
     *            the {@link File} file from where to load the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws IOException
     *             if there was I/O error loading the layout.
     */
    public void loadLayout(String name, File file,
            PropertyChangeListener... listeners) throws IOException {
        dock.loadLayout(name, file, listeners);
        dock.addEditorDock(importPanelDock);
    }

    /**
     * Loads the previously saved layout with the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param stream
     *            the {@link InputStream} stream from where to load the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws IOException
     *             if there was I/O error loading the layout.
     */
    public void loadLayout(String name, InputStream stream,
            PropertyChangeListener... listeners) throws IOException {
        dock.loadLayout(name, stream, listeners);
        dock.addEditorDock(importPanelDock);
    }

    /**
     * Returns the current active layout.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link LayoutTask}.
     */
    @OnAwt
    public LayoutTask getCurrentLayout() {
        return dock.getCurrentLayout();
    }

    /**
     * Sets a theme.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the theme name.
     */
    @OnAwt
    public void setTheme(String name) {
        dock.setTheme(name);
    }

    @Override
    @OnAwt
    public void openDialog() {
        importPanelDock.getImportPanel().requestFocus();
        super.openDialog();
    }

    @Override
    @OnAwt
    public void restoreDialog() {
        try {
            importPanelDock.restoreInput();
        } catch (PropertyVetoException e) {
        }
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(VetoableChangeListener)
     * @see SimpleDialog#addVetoableChangeListener(VetoableChangeListener)
     */
    @Override
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        importPanelDock.addVetoableChangeListener(listener);
        super.addVetoableChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#removeVetoableChangeListener(VetoableChangeListener)
     * @see SimpleDialog#removeVetoableChangeListener(VetoableChangeListener)
     */
    @Override
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        importPanelDock.removeVetoableChangeListener(listener);
        super.removeVetoableChangeListener(listener);
    }

    /**
     * @see SpreadsheetImportPanel#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     * @see SimpleDialog#addVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    @Override
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanelDock.addVetoableChangeListener(propertyName, listener);
        super.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see SpreadsheetImportPanel#removeVetoableChangeListener(String,
     *      VetoableChangeListener)
     * @see SimpleDialog#removeVetoableChangeListener(String,
     *      VetoableChangeListener)
     */
    @Override
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        importPanelDock.removeVetoableChangeListener(propertyName, listener);
        super.removeVetoableChangeListener(propertyName, listener);
    }

    private static final String IMPORT_ACTION_NAME = "import_action";

    private void createDialog0(JFrame frame,
            SpreadsheetImporterFactory importerFactory) {
        Locale locale = getLocale();
        dock.createDock(frame);
        setApproveActionName(IMPORT_ACTION_NAME);
        importPanelDock.setLocale(locale);
        importPanelDock.createPanel(parent, properties, importerFactory);
        importPanelDock.addPropertyChangeListener(propertyChangeListener);
        setFieldsPanel(dock.getAWTComponent());
        previewPanelDock.setLocale(locale);
        previewPanelDock.createPanel(parent, importerFactory);
        previewPanelDock.setProperties(properties);
        dock.addViewDock(previewPanelDock);
        dock.addEditorDock(importPanelDock);
        validListener.installDialog(this);
        setTexts(texts);
    }

}
