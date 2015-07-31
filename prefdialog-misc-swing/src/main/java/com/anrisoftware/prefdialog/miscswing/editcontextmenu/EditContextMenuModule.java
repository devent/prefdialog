/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.editcontextmenu;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.resources.images.images.ImagesResourcesModule;
import com.anrisoftware.resources.images.mapcached.ResourcesImagesCachedMapModule;
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the edit context menu,
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 * @see EditContextMenuFactory
 */
public final class EditContextMenuModule extends AbstractModule {

    /**
     * Returns the edit context menu factory.
     *
     * @return the {@link EditContextMenuFactory}.
     */
    public static EditContextMenuFactory getSimpleDialogFactory() {
        return SingletonHolder.editContextMenuFactory;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] {
                new EditContextMenuModule(), new MnemonicModule(),
                new TextsResourcesDefaultModule(), new ImagesResourcesModule(),
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final EditContextMenuFactory editContextMenuFactory = injector
                .getInstance(EditContextMenuFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(EditContextMenu.class,
                EditContextMenu.class).build(EditContextMenuFactory.class));
    }

}
