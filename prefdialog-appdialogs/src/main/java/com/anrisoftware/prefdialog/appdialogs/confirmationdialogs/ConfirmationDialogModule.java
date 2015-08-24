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
package com.anrisoftware.prefdialog.appdialogs.confirmationdialogs;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
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
 * Installs the confirmation dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public final class ConfirmationDialogModule extends AbstractModule {

    /**
     * Returns the Ok/Cancel confirmation dialog factory.
     *
     * @return the {@link OkCancelConfirmationDialogFactory}.
     */
    public static OkCancelConfirmationDialogFactory getOkCancelDialogFactory() {
        return SingletonHolder.okCancelDialogFactory;
    }

    /**
     * Returns the resource unsaved confirmation dialog factory.
     *
     * @return the {@link UnsavedConfirmationDialogFactory}.
     */
    public static UnsavedConfirmationDialogFactory getUnsavedDialogFactory() {
        return SingletonHolder.unsavedDialogFactory;
    }

    /**
     * Returns the Injector with all necessarily modules installed to inject the
     * confirmation dialog.
     *
     * @return the {@link Injector}.
     */
    public static Injector getConfirmationDialogInjector() {
        return SingletonHolder.injector;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] {
                new ConfirmationDialogModule(), new SimpleDialogModule(),
                new MnemonicModule(), new AnnotationsModule(),
                new BeansModule(), new TextsResourcesDefaultModule(),
                new TemplatesResourcesModule(),
                new TemplatesDefaultMapsModule(), new STWorkerModule(),
                new STDefaultPropertiesModule(), new ImagesResourcesModule(),
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final OkCancelConfirmationDialogFactory okCancelDialogFactory = injector
                .getInstance(OkCancelConfirmationDialogFactory.class);

        public static final UnsavedConfirmationDialogFactory unsavedDialogFactory = injector
                .getInstance(UnsavedConfirmationDialogFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(
                OkCancelConfirmationDialog.class,
                OkCancelConfirmationDialog.class).build(
                OkCancelConfirmationDialogFactory.class));
        install(new FactoryModuleBuilder().implement(
                UnsavedConfirmationDialog.class,
                UnsavedConfirmationDialog.class).build(
                UnsavedConfirmationDialogFactory.class));
    }

}
