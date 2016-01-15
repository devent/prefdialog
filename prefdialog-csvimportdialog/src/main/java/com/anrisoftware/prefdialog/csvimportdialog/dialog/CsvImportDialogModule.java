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
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import com.anrisoftware.globalpom.csvimport.CsvImportModule;
import com.anrisoftware.globalpom.data.DataModule;
import com.anrisoftware.globalpom.dataimport.DataImportModule;
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelModule;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.previewpanel.PreviewPanelModule;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule;
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
 * @see CsvImportPanel
 * @see CsvImportPanelFactory
 * @see CsvImportDialogModule
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvImportDialogModule extends AbstractModule {

    /**
     * @see #getFactory()
     */
    public static CsvImportDialogFactory getCsvImportDialogFactory() {
        return getFactory();
    }

    /**
     * Returns the CSV import dialog factory.
     *
     * @return the {@link CsvImportDialogFactory}.
     */
    public static CsvImportDialogFactory getFactory() {
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
    public static CsvPanelPropertiesFactory getCsvImportPropertiesFactory() {
        return getPropertiesFactory();
    }

    /**
     * Creates and returns the panel properties factory.
     *
     * @return the {@link CsvPanelPropertiesFactory}.
     */
    public static CsvPanelPropertiesFactory getPropertiesFactory() {
        return SingletonHolder.propertiesFactory;
    }

    private static class SingletonHolder {

        public static final Injector injector = Guice.createInjector(
                new CsvImportDialogModule(), new SimpleDialogModule(),
                new CoreFieldComponentModule(), new ComboBoxHistoryModule(),
                new DockingFramesModule(), new CsvImportModule(),
                new DataModule(), new DataImportModule(),
                new TextsResourcesDefaultModule(), new ImagesResourcesModule(),
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule());

        public static final CsvImportDialogFactory factory = injector
                .getInstance(CsvImportDialogFactory.class);

        public static final CsvPanelPropertiesFactory propertiesFactory = injector
                .getInstance(CsvPanelPropertiesFactory.class);
    }

    @Override
    protected void configure() {
        install(new CsvImportPanelModule());
        install(new PreviewPanelModule());
        install(new FactoryModuleBuilder().implement(CsvImportDialog.class,
                CsvImportDialog.class).build(CsvImportDialogFactory.class));
        install(new FactoryModuleBuilder().implement(PropertiesWorker.class,
                PropertiesWorker.class).build(PropertiesWorkerFactory.class));
    }

}
