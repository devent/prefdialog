/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of gsclock-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.tables;

import javax.swing.DefaultListModel;
import javax.swing.undo.UndoableEdit;

import com.anrisoftware.prefdialog.miscswing.itemswindow.ItemsWindow;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create the set item undoable action.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface SetItemUndoableActionFactory {

    /**
     * Creates the set item undoable action.
     *
     * @param itemsWindow
     *            the {@link ItemsWindow}.
     *
     * @param model
     *            the {@link DefaultListModel} of the item.
     *
     * @param oldItem
     *            the old item.
     *
     * @param newItem
     *            the new item.
     *
     * @param index
     *            the index of the item.
     *
     * @return the {@link UndoableEdit}.
     */
    @SuppressWarnings("rawtypes")
    UndoableEdit create(@Assisted ItemsWindow itemsWindow,
            @Assisted DefaultListModel model,
            @Assisted("oldResource") Object oldItem,
            @Assisted("newResource") Object newItem, @Assisted int index);
}
