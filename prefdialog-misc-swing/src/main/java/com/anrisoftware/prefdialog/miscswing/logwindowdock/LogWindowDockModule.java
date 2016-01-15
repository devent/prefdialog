/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.logwindowdock;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the log window dock factory.
 *
 * @see LogWindowDockFactory
 * @see ErrorNodeFactory
 * @see InfoNodeFactory
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LogWindowDockModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(LogWindowDock.class,
                LogWindowDock.class).build(LogWindowDockFactory.class));
        install(new FactoryModuleBuilder().implement(ErrorNode.class,
                ErrorNode.class).build(ErrorNodeFactory.class));
        install(new FactoryModuleBuilder().implement(InfoNode.class,
                InfoNode.class).build(InfoNodeFactory.class));
    }

}
