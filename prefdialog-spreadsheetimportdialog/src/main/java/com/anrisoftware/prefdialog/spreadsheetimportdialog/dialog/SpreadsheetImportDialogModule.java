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

import com.anrisoftware.globalpom.csvimport.CsvImportModule;
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanel;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelFactory;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelModule;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesFactory;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.PreviewPanelModule;
import com.anrisoftware.resources.images.images.ImagesResourcesModule;
import com.anrisoftware.resources.images.mapcached.ResourcesImagesCachedMapModule;
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV import dialog factory and dependent modules.
 *
 * @see SpreadsheetImportPanel
 * @see SpreadsheetImportPanelFactory
 * @see SpreadsheetImportDialogModule
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SpreadsheetImportDialogModule extends AbstractModule {

    /**
     * @see #getFactory()
     */
    public static SpreadsheetImportDialogFactory getCsvImportDialogFactory() {
        return getFactory();
    }

    /**
     * Returns the CSV import dialog factory.
     *
     * @return the {@link SpreadsheetImportDialogFactory}.
     */
    public static SpreadsheetImportDialogFactory getFactory() {
        return SingletonHolder.factory;
    }

    /**
     * @see #getInjector()
     */
    public static Injector getCsvImportDialogInjector() {
        return getInjector();
    }

    /**
     * Returns the CSV import dialog Guice injector.
     *
     * @return the {@link Injector}.
     */
    public static Injector getInjector() {
        return SingletonHolder.injector;
    }

    /**
     * @see #getPropertiesFactory()
     */
    public static SpreadsheetPanelPropertiesFactory getCsvImportPropertiesFactory() {
        return getPropertiesFactory();
    }

    /**
     * Creates and returns the panel properties factory.
     *
     * @return the {@link SpreadsheetPanelPropertiesFactory}.
     */
    public static SpreadsheetPanelPropertiesFactory getPropertiesFactory() {
        return SingletonHolder.propertiesFactory;
    }

    private static class SingletonHolder {

        public static final Injector injector = Guice.createInjector(
                new SpreadsheetImportDialogModule(), new SimpleDialogModule(),
                new CoreFieldComponentModule(), new ComboBoxHistoryModule(),
                new DockingFramesModule(), new CsvImportModule(),
                new TextsResourcesDefaultModule(), new ImagesResourcesModule(),
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule());

        public static final SpreadsheetImportDialogFactory factory = injector
                .getInstance(SpreadsheetImportDialogFactory.class);

        public static final SpreadsheetPanelPropertiesFactory propertiesFactory = injector
                .getInstance(SpreadsheetPanelPropertiesFactory.class);
    }

    @Override
    protected void configure() {
        install(new SpreadsheetImportPanelModule());
        install(new PreviewPanelModule());
        install(new FactoryModuleBuilder().implement(SpreadsheetImportDialog.class,
                SpreadsheetImportDialog.class).build(SpreadsheetImportDialogFactory.class));
        install(new FactoryModuleBuilder().implement(PropertiesWorker.class,
                PropertiesWorker.class).build(PropertiesWorkerFactory.class));
    }

}
