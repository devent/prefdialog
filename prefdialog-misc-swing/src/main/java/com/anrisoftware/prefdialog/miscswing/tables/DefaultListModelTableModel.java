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
package com.anrisoftware.prefdialog.miscswing.tables;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.itemswindow.ItemsWindow;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Table model that uses the values from a list model.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@SuppressWarnings("serial")
public abstract class DefaultListModelTableModel<T> extends DefaultTableModel
        implements ListDataListener {

    private final String[] columnNames;

    private final Class<?>[] columnTypes;

    private final boolean[] columnEditable;

    @Inject
    private StringEquals stringEquals;

    @Inject
    private ValueEquals valueEquals;

    private DefaultListModel<T> model;

    private UndoManager undoManager;

    private boolean lockSetValue;

    private Texts texts;

    private Locale locale;

    private ItemsWindow<T> itemsWindow;

    /**
     * Sets the column names, types and editable columns.
     *
     * @param columnNames
     *            the column names, the names are looked up in the texts
     *            resources.
     *
     * @param columnTypes
     *            the column types.
     *
     * @param columnEditable
     *            the editable columns.
     */
    public DefaultListModelTableModel(String[] columnNames,
            Class<?>[] columnTypes, boolean[] columnEditable) {
        this.columnNames = columnNames.clone();
        this.columnTypes = columnTypes.clone();
        this.columnEditable = columnEditable.clone();
        this.lockSetValue = false;
    }

    public void setUndoManager(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    public void setTexts(Texts texts) {
        this.texts = texts;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Sets the list model that provides the data for the table model.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param model
     *            the {@link DefaultListModel} or {@code null}.
     */
    @OnAwt
    public void setListModel(DefaultListModel<T> model) {
        DefaultListModel<T> oldModel = this.model;
        this.model = model;
        fireTableDataChanged();
        if (oldModel != null) {
            oldModel.removeListDataListener(this);
        }
        if (model != null) {
            model.addListDataListener(this);
        }
    }

    /**
     * Sets the items window.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param itemsWindow
     *            the {@link ItemsWindow}.
     */
    @OnAwt
    public void setItemsWindow(ItemsWindow<T> itemsWindow) {
        this.itemsWindow = itemsWindow;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return texts.getResource(columnNames[column], locale).getText();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return columnEditable[column];
    }

    @Override
    public int getRowCount() {
        return model == null ? 0 : model.getSize();
    }

    @Override
    public Object getValueAt(int row, int column) {
        T item = model.getElementAt(row);
        if (column == -1) {
            return item;
        }
        return getValue(item, row, column);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (row >= getRowCount()) {
            return;
        }
        if (lockSetValue) {
            this.lockSetValue = false;
            return;
        }
        T oldItem = model.getElementAt(row);
        T item = createItem(oldItem);
        if (setValueAt(value, row, column, model, oldItem, item)) {
            UndoableEdit edit;
            edit = createUndoableEdit(itemsWindow, model, oldItem, item, row);
            informResourceUpdated(model, item, row, edit);
        }
    }

    /**
     * Updates the table on inserted items.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
     */
    @Override
    @OnAwt
    public void intervalAdded(ListDataEvent e) {
        this.lockSetValue = true;
        int index0 = e.getIndex0();
        int index1 = e.getIndex1();
        fireTableRowsInserted(index0, index1);
        lockSetValue = false;
    }

    /**
     * Updates the table on removed items.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
     */
    @Override
    @OnAwt
    public void intervalRemoved(ListDataEvent e) {
        this.lockSetValue = true;
        int index0 = e.getIndex0();
        int index1 = e.getIndex1();
        fireTableRowsDeleted(index0, index1);
        lockSetValue = false;
    }

    /**
     * Updates the table on changed items.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
     */
    @Override
    @OnAwt
    public void contentsChanged(ListDataEvent e) {
        int index0 = e.getIndex0();
        int index1 = e.getIndex1();
        fireTableRowsUpdated(index0, index1);
    }

    /**
     * Returns the value from the item.
     *
     * @param item
     *            the item.
     *
     * @param row
     *            the row {@link Integer} index.
     *
     * @param column
     *            the column {@link Integer} index.
     *
     * @return the {@link Object} value.
     */
    protected abstract Object getValue(T item, int row, int column);

    /**
     * Sets the value to the item.
     *
     * @param value
     *            the {@link Object} value.
     *
     * @param row
     *            the row {@link Integer} index.
     *
     * @param column
     *            the column {@link Integer} index.
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
     * @return {@code true} if the item was changed.
     */
    protected abstract boolean setValueAt(Object value, int row, int column,
            DefaultListModel<T> model, T oldItem, T newItem);

    /**
     * Creates a new item.
     *
     * @param oldItem
     *            the old item.
     *
     * @return the new item.
     */
    protected abstract T createItem(T oldItem);

    /**
     * Creates the undoable edit for the undo manager to undo the value change.
     *
     * @param itemsWindow
     *            the {@link ItemsWindow} to select the undo/redo item.
     *
     * @param model
     *            the {@link DefaultListModel} to undo/redo the item.
     *
     * @param oldItem
     *            the old item.
     *
     * @param newItem
     *            the new item.
     *
     * @param row
     *            the row {@link Integer} index.
     *
     * @return the {@link UndoableEdit}.
     */
    protected abstract UndoableEdit createUndoableEdit(
            ItemsWindow<T> itemsWindow, DefaultListModel<T> model, T oldItem,
            T newItem, int row);

    /**
     * Informs the model and the undo manager that the item was changed.
     *
     * @param model
     *            the {@link DefaultListModel} to undo/redo the item.
     *
     * @param item
     *            the item.
     *
     * @param rowIndex
     *            the row {@link Integer} index.
     *
     * @param edit
     *            the {@link UndoableEdit} for the undo manager.
     */
    protected final void informResourceUpdated(DefaultListModel<T> model,
            T item, int rowIndex, UndoableEdit edit) {
        model.setElementAt(item, rowIndex);
        undoManager.addEdit(edit);
    }

    /**
     * Compare the new string value with the old string value.
     */
    protected final boolean isStringEquals(Object newValue, String old) {
        return stringEquals.isStringEquals(newValue, old);
    }

    /**
     * Compare the new value with the old value.
     */
    protected final boolean isValueEquals(Object newValue, Object old) {
        return valueEquals.isValueEquals(newValue, old);
    }

}
