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
package com.anrisoftware.prefdialog.miscswing.itemswindow;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Selects items in a table.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@OnAwt
public class DefaultTableItemsWindow<ItemType> implements ItemsWindow<ItemType> {

    private final JTable table;

    /**
     * Sets the table.
     *
     * @param table
     *            the {@link JTable}.
     */
    public DefaultTableItemsWindow(JTable table) {
        this.table = table;
    }

    @Override
    public void stopEditing() {
        table.removeEditor();
    }

    @Override
    public void setSelectedIndices(int[] indices) {
        for (int i : indices) {
            table.getSelectionModel().addSelectionInterval(i, i);
        }
    }

    @Override
    public void setSelectedIndex(int index) {
        table.getSelectionModel().setSelectionInterval(index, index);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(index, 0,
                true)));
    }

    @Override
    public int getSelectedIndex() {
        return table.getSelectedRow();
    }

    @Override
    public List<ItemType> getSelectedItems() {
        List<ItemType> list = new ArrayList<ItemType>();
        TableModel model = table.getModel();
        for (int index : getSelectedIndices()) {
            @SuppressWarnings("unchecked")
            ItemType item = (ItemType) model.getValueAt(index, -1);
            list.add(item);
        }
        return list;
    }

    @Override
    public int[] getSelectedIndices() {
        return table.getSelectedRows();
    }

    @Override
    public void selectAll() {
        int i0 = 0;
        int i1 = table.getModel().getRowCount();
        table.getSelectionModel().setSelectionInterval(i0, i1 - 1);
    }

    @Override
    public void clearSelection() {
        ListSelectionModel model = table.getSelectionModel();
        model.clearSelection();
    }

}
