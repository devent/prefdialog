package com.anrisoftware.prefdialog.miscswing.multichart.model;

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

    /**
     * Marker for all columns.
     */
    public static final int ALL_COLUMNS = -1;

    private static final String COLUMN = "column";

    private static final String LAST_ROW = "last row";

    private static final String FIRST_ROW = "first row";

    private static final String TYPE = "type";

    private static final String OFFSET = "offset";

    private final EventType type;

    private final int firstRow;

    private final int lastRow;

    private final int column;

    private final int offset;

    /**
     * Event that the whole chart model have been changed.
     * 
     * @param source
     *            the source {@link ChartModel}.
     */
    public ChartModelEvent(ChartModel source) {
        this(source, EventType.UPDATED, 0, source.getMaximumRowCount() - 1,
                ALL_COLUMNS, source.getOffset());
    }

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
     * 
     * @param offset
     *            the current offset of the data.
     */
    public ChartModelEvent(ChartModel source, EventType type, int fistRow,
            int lastRow, int column, int offset) {
        super(source);
        this.type = type;
        this.firstRow = fistRow;
        this.lastRow = lastRow;
        this.column = column;
        this.offset = offset;
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

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
                .append(TYPE, type).append(FIRST_ROW, firstRow)
                .append(LAST_ROW, lastRow).append(COLUMN, column)
                .append(OFFSET, offset).toString();
    }
}
