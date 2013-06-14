package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.JTable;

public interface SpreadsheetTableFactory {

	SpreadsheetTable create(JTable table, SpreadsheetModel model,
			ViewRange range);
}
