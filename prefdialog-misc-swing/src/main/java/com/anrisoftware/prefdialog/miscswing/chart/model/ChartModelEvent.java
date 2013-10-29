package com.anrisoftware.prefdialog.miscswing.chart.model;

import java.util.EventObject;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Notify the listeners that the data values have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ChartModelEvent extends EventObject {

	public static enum EventType {

		INSERTED,

		UPDATED,

		DELETED
	}

	private static final String COLUMN = "column";

	private static final String LAST_ROW = "last row";

	private static final String FIRST_ROW = "first row";

	private static final String TYPE = "type";

	public static final int ALL_COLUMNS = -1;

	private final EventType type;

	private final int firstRow;

	private final int lastRow;

	private final int column;

	/**
	 * The data values from the first row to the last row in the specified
	 * column have been updated.
	 * 
	 * @param source
	 *            the source {@link ChartModel}.
	 * 
	 * @param type
	 *            the {@link EventType} type of change.
	 * 
	 * @param fistRow
	 *            the first row of change.
	 * 
	 * @param lastRow
	 *            the last row of change.
	 * 
	 * @param column
	 *            the column index of change or {@link #ALL_COLUMNS} indicating
	 *            that all columns have been updated.
	 */
	public ChartModelEvent(ChartModel source, EventType type, int fistRow,
			int lastRow, int column) {
		super(source);
		this.type = type;
		this.firstRow = fistRow;
		this.lastRow = lastRow;
		this.column = column;
	}

	public EventType getType() {
		return type;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append(TYPE, type).append(FIRST_ROW, firstRow)
				.append(LAST_ROW, lastRow).append(COLUMN, column).toString();
	}
}
