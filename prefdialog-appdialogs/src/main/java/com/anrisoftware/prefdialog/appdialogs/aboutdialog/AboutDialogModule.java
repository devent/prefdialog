/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.aboutdialog;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogFactory;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogModule;
import com.anrisoftware.prefdialog.appdialogs.dialogheader.DialogHeaderModule;
import com.anrisoftware.prefdialog.miscswing.editcontextmenu.EditContextMenuModule;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule;
import com.anrisoftware.resources.images.images.ImagesResourcesModule;
import com.anrisoftware.resources.images.mapcached.ResourcesImagesCachedMapModule;
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule;
import com.anrisoftware.resources.templates.maps.TemplatesDefaultMapsModule;
import com.anrisoftware.resources.templates.templates.TemplatesResourcesModule;
import com.anrisoftware.resources.templates.worker.STDefaultPropertiesModule;
import com.anrisoftware.resources.templates.worker.STWorkerModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the about dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 * @see AppDialogFactory
 */
public class AboutDialogModule extends AbstractModule {

    /**
     * Returns the about dialog factory.
     *
     * @return the {@link AboutDialogFactory}.
     */
    public static AboutDialogFactory getSimpleDialogFactory() {
        return SingletonHolder.dialogFactory;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] {
                new AboutDialogModule(), new AppDialogModule(),
                new DialogHeaderModule(), new SimpleDialogModule(),
                new EditContextMenuModule(), new MnemonicModule(),
                new AnnotationsModule(), new BeansModule(),
                new TextsResourcesDefaultModule(),
                new TemplatesResourcesModule(),
                new TemplatesDefaultMapsModule(), new STWorkerModule(),
                new STDefaultPropertiesModule(), new ImagesResourcesModule(),
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final AboutDialogFactory dialogFactory = injector
                .getInstance(AboutDialogFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(AboutDialog.class,
                AboutDialog.class).build(AboutDialogFactory.class));
    }

}
