/*
 * Copyright 2011-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of gscalculator-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.undomanager;

import javax.inject.Inject;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import com.google.inject.assistedinject.Assisted;

/**
 * Data edit that can be undone.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class UndoableValueEdit extends AbstractUndoableEdit {

    private final UndoableValue source;

    private final Object oldValue;

    private final Object newValue;

    private final int index;

    /**
     * @see UndoableValueEditFactory#create(UndoableValue, int, Object, Object)
     */
    @Inject
    UndoableValueEdit(@Assisted UndoableValue source, @Assisted int index,
            @Assisted("oldValue") Object oldValue,
            @Assisted("newValue") Object newValue) {
        this.source = source;
        this.index = index;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        source.redoValue(index, newValue);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        source.undoValue(index, oldValue);
    }

}
