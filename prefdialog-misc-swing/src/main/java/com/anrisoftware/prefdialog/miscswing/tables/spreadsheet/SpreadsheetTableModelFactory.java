package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.table.TableModel;

public interface SpreadsheetTableModelFactory {

	SpreadsheetTableModel create(TableModel model, ViewRange range);
}
