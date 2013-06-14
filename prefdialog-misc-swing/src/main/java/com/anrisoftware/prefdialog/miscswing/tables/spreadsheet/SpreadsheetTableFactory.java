package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public interface SpreadsheetTableFactory {

	SpreadsheetTable create(JTable table, TableModel model, ViewRange range);
}
