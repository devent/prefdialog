/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportException;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporter;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Loads the preview data.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@SuppressWarnings("serial")
class PreviewDataTableModel extends AbstractTableModel {

    private final List<List<String>> rows;

    private final List<String> headers;

    private final int maxPrefiewRows;

    private int columnCount;

    PreviewDataTableModel() {
        this.rows = new ArrayList<List<String>>();
        this.headers = new ArrayList<String>();
        this.maxPrefiewRows = 25;
        this.columnCount = 0;
    }

    /**
     * Updates the preview for the specified importer.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param importer
     *            the {@link SpreadsheetImporter} or {@code null}.
     *
     * @throws SpreadsheetImportException
     *             if there was an error loading the preview data.
     */
    @OnAwt
    public void setImporter(SpreadsheetImporter importer)
            throws SpreadsheetImportException {
        if (importer == null) {
            this.rows.clear();
            this.headers.clear();
            this.columnCount = 0;
            fireTableStructureChanged();
        } else {
            loadData(importer);
            fireTableStructureChanged();
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        if (rows.size() == 0) {
            return 0;
        } else {
            return columnCount;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (headers.size() > 0 && column < headers.size()) {
            return headers.get(column);
        } else {
            return super.getColumnName(column);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= rows.size()) {
            return null;
        }
        List<String> cols = rows.get(rowIndex);
        if (columnIndex >= cols.size()) {
            return null;
        }
        return cols.get(columnIndex);
    }

    private void loadData(SpreadsheetImporter importer)
            throws SpreadsheetImportException {
        int i = 0;
        rows.clear();
        headers.clear();
        int cols = 0;
        importer.open();
        if (importer.getProperties().isHaveHeader()) {
            headers.addAll(importer.getHeaders());
        }
        while (importer.loadNext() && i < maxPrefiewRows) {
            List<String> values = importer.getValues();
            rows.add(values);
            cols = Math.max(cols, values.size());
            i++;
        }
        this.columnCount = cols;
    }

}
