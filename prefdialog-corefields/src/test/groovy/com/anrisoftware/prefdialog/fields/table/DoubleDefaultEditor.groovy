/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.table

import java.awt.Component

import javax.swing.DefaultCellEditor
import javax.swing.JFormattedTextField
import javax.swing.JTable
import javax.swing.table.TableCellEditor

import com.anrisoftware.prefdialog.annotations.TypedTableCellEditor

/**
 * Custom table column editor with a public standard constructor.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class DoubleDefaultEditor implements TypedTableCellEditor {

    @Override
    Class<?> getType() {
        return Double.class;
    }

    @Override
    public TableCellEditor getEditor() {
        return new DefaultCellEditor(new JFormattedTextField()) {
            Component getTableCellEditorComponent(JTable table, Object value,
                    boolean isSelected, int row, int column) {
                def v = ((double)value) * 10
                def component = super.getTableCellEditorComponent(table, v, isSelected, row, column)
                return component
            };
        };
    }
}
