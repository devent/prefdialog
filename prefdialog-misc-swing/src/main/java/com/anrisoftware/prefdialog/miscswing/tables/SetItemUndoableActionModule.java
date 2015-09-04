package com.anrisoftware.prefdialog.miscswing.tables;

import javax.swing.undo.UndoableEdit;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the set item undoable action.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class SetItemUndoableActionModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(UndoableEdit.class,
                SetItemUndoableAction.class).build(
                SetItemUndoableActionFactory.class));
    }

}
