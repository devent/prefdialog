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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel;

import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs preview panel factory and dependent modules.
 *
 * @see SpreadsheetPreviewPanel
 * @see SpreadsheetPreviewPanelFactory
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class SpreadsheetPreviewPanelModule extends AbstractModule {

    /**
     * @see #getFactory()
     */
    public static SpreadsheetPreviewPanelFactory getPreviewPanelFactory() {
        return getFactory();
    }

    /**
     * Creates and returns the preview panel factory.
     *
     * @return the {@link SpreadsheetPreviewPanelFactory}.
     */
    public static SpreadsheetPreviewPanelFactory getFactory() {
        return SingletonHolder.factory;
    }

    private static class SingletonHolder {

        public static final Injector injector = Guice.createInjector(
                new SpreadsheetPreviewPanelModule(),
                new TextsResourcesDefaultModule());

        public static final SpreadsheetPreviewPanelFactory factory = injector
                .getInstance(SpreadsheetPreviewPanelFactory.class);

    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(
                SpreadsheetPreviewPanel.class, SpreadsheetPreviewPanel.class)
                .build(SpreadsheetPreviewPanelFactory.class));
    }
}
