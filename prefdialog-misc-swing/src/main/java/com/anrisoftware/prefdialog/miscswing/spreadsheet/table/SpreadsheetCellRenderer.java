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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Not show selection on not editable cells.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public final class SpreadsheetCellRenderer extends DefaultTableCellRenderer {

    private Color unselectedForeground;

    private Color unselectedBackground;

    private Color uneditableBackground;

    private Color uneditableForeground;

    private boolean showUneditableColumnOnlyFirst;

    public SpreadsheetCellRenderer() {
        this.uneditableForeground = null;
        this.uneditableBackground = null;
        this.showUneditableColumnOnlyFirst = false;
    }

    public void setUneditableBackground(Color c) {
        this.uneditableBackground = c;
    }

    public void setShowUneditableColumnOnlyFirst(boolean b) {
        this.showUneditableColumnOnlyFirst = b;
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        this.unselectedForeground = c;
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        this.unselectedBackground = c;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
        if (uneditableBackground == null) {
            uneditableBackground = table.getTableHeader().getBackground();
        }
        if (uneditableForeground == null) {
            uneditableForeground = table.getTableHeader().getForeground();
        }
        TableModel model = table.getModel();
        boolean editable = model.isCellEditable(row, column);
        if (editable) {
            setupEditable(table, isSelected);
        }
        if (!editable) {
            setupUneditable(table, isSelected, row, column);
        }
        return this;
    }

    private void setupEditable(JTable table, boolean isSelected) {
        if (isSelected) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            super.setForeground(unselectedForeground);
            super.setBackground(unselectedBackground);
        }
    }

    private void setupUneditable(JTable table, boolean isSelected, int row,
            int column) {
        if (showUneditableColumnOnlyFirst && column > 0) {
            setupEditable(table, isSelected);
            return;
        }
        if (isSelected) {
            super.setForeground(unselectedForeground);
            if (uneditableBackground == null) {
                super.setBackground(unselectedBackground);
            } else {
                super.setBackground(uneditableBackground);
            }
        }
        if (!isSelected) {
            super.setBackground(uneditableBackground);
        }
    }

}
