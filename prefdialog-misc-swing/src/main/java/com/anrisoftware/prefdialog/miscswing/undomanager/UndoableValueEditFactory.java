package com.anrisoftware.prefdialog.miscswing.undomanager;

import javax.swing.undo.UndoableEdit;

import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create the edit data resource undo edit.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public interface UndoableValueEditFactory {

    /**
     * Creates the edit data resource undo edit.
     *
     * @param source
     *            the source {@link UndoableValue}.
     *
     * @param index
     *            the {@link Integer} index of the resource.
     *
     * @param oldValue
     *            the old value.
     *
     * @param newValue
     *            the new value.
     *
     * @return the {@link UndoableEdit}.
     */
    @SuppressWarnings("rawtypes")
    UndoableEdit create(UndoableValue source, int index,
            @Assisted("oldValue") Object oldValue,
            @Assisted("newValue") Object newValue);

}
