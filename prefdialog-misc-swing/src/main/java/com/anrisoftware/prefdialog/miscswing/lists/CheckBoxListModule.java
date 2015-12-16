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
