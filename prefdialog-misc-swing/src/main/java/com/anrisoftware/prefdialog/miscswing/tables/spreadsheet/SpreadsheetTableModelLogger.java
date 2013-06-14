package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import static org.apache.commons.lang3.Validate.notNull;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SpreadsheetTableModel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class SpreadsheetTableModelLogger extends AbstractLogger {

	private static final String RANGE_NULL = "Range cannot be null.";

	/**
	 * Create logger for {@link SpreadsheetTableModel}.
	 */
	public SpreadsheetTableModelLogger() {
		super(SpreadsheetTableModel.class);
	}

	void checkViewRange(ViewRange range) {
		notNull(range, RANGE_NULL);
	}
}
