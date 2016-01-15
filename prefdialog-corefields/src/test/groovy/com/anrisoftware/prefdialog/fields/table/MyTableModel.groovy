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

import javax.swing.table.DefaultTableModel

/**
 * Custom table model with a public standard constructor.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class MyTableModel extends DefaultTableModel {

    MyTableModel() {
        super([
            "One",
            "Two",
            "Three",
            "Four"] as Object[], 0)
        addRow(["aaa1", "bbb1", 1/5, false] as Object[])
        addRow(["aaa2", "bbb2", 1/4, true] as Object[])
        addRow(["aaa3", "bbb3", 1/3, false] as Object[])
        addRow(["aaa4", "bbb4", 1/2, true] as Object[])
    }

    @Override
    Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Boolean.class;
        }
    }

    @Override
    boolean isCellEditable(int row, int column) {
        return true
    }
}
