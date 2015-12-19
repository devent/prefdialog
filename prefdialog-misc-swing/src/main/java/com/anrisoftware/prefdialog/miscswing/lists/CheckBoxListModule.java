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
package com.anrisoftware.prefdialog.miscswing.lists;

import com.anrisoftware.prefdialog.miscswing.lists.CheckBoxList.CheckBoxListFactory;
import com.anrisoftware.prefdialog.miscswing.lists.CheckListItem.CheckListItemFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the check box items list.
 *
 * @see CheckBoxListFactory
 * @see CheckListItemFactory
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.6
 */
public class CheckBoxListModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(CheckBoxList.class,
                CheckBoxList.class).build(CheckBoxListFactory.class));
        install(new FactoryModuleBuilder().implement(CheckListItem.class,
                CheckListItem.class).build(CheckListItemFactory.class));
    }

}
