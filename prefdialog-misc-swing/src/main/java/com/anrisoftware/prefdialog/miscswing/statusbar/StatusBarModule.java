/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the status bar factory.
 * 
 * @see StatusBarFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class StatusBarModule extends AbstractModule {

    /**
     * Returns the status bar factory.
     * 
     * @return the {@link StatusBarFactory}.
     */
    public static StatusBarFactory getStatusBarFactory() {
        return instance.statusBarFactory;
    }

    private static class instance {

        public static Injector injector = Guice
                .createInjector(new StatusBarModule());

        public static StatusBarFactory statusBarFactory = injector
                .getInstance(StatusBarFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(StatusBar.class,
                StatusBar.class).build(StatusBarFactory.class));
    }

}