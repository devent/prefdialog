/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.JTable
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellRenderer

import com.anrisoftware.prefdialog.annotations.TypedTableCellRenderer

/**
 * Custom table column renderer with a public standard constructor.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class DoubleDefaultRenderer implements TypedTableCellRenderer {

    @Override
    Class<?> getType() {
        return Double.class;
    }

    @Override
    TableCellRenderer getRenderer() {
        return new DefaultTableCellRenderer() {
            Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
                def v = ((double)value) * 10
                setText("$v")
                return this
            };
        };
    }
}
