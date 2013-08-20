package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.COLUMN_INDEX_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.ROW_INDEX_PROPERTY;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.table.AbstractTableModel;

/**
 * Returns the row and column index, the maximum rows and columns for a table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class NavigationModelImpl extends AbstractTableModel implements NavigationModel {

	private static final int ROW_INDEX_COLUMN = 0;

	private static final int MAXIMUM_ROW_COLUMN = 1;

	private static final int COLUMN_INDEX_COLUMN = 2;

	private static final int MAXIMUM_COLUMN_COLUMN = 3;

	private static boolean[] EDITABLE = { true, false, true, false };

	private static Class<?>[] TYPES = { Integer.class, Integer.class,
			Integer.class, Integer.class };

	private final PropertyChangeSupport propertySupport;

	private int rowIndex;

	private int columnIndex;

	private int maximumRow;

	private int maximumColumn;

	private int minimumRow;

	private int minimumColumn;

	NavigationModelImpl() {
		this.propertySupport = new PropertyChangeSupport(this);
		this.rowIndex = 0;
		this.columnIndex = 0;
		this.maximumRow = 0;
		this.maximumColumn = 0;
		this.minimumRow = 0;
		this.maximumColumn = 0;
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex < 0) {
			return;
		}
		if (rowIndex > maximumRow) {
			return;
		}
		int oldValue = this.rowIndex;
		this.rowIndex = rowIndex;
		fireTableCellUpdated(0, ROW_INDEX_COLUMN);
		propertySupport.firePropertyChange(ROW_INDEX_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public void setColumnIndex(int columnIndex) {
		if (columnIndex < 0) {
			return;
		}
		if (columnIndex > maximumColumn) {
			return;
		}
		int oldValue = this.columnIndex;
		this.columnIndex = columnIndex;
		fireTableCellUpdated(0, COLUMN_INDEX_COLUMN);
		propertySupport.firePropertyChange(COLUMN_INDEX_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getColumnIndex() {
		return columnIndex;
	}

	@Override
	public void setMaximumColumn(int maximumColumn) {
		int oldValue = this.maximumColumn;
		this.maximumColumn = maximumColumn;
		fireTableCellUpdated(0, MAXIMUM_COLUMN_COLUMN);
		propertySupport.firePropertyChange(MAXIMUM_COLUMN_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getMaximumColumn() {
		return maximumColumn;
	}

	@Override
	public void setMaximumRow(int maximumRow) {
		int oldValue = this.maximumRow;
		this.maximumRow = maximumRow;
		fireTableCellUpdated(0, MAXIMUM_ROW_COLUMN);
		propertySupport.firePropertyChange(MAXIMUM_ROW_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getMaximumRow() {
		return maximumRow;
	}

	@Override
	public void setMinimumColumn(int minimumColumn) {
		int oldValue = this.minimumColumn;
		this.minimumColumn = minimumColumn;
		propertySupport.firePropertyChange(MINIMUM_COLUMN_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getMinimumColumn() {
		return minimumColumn;
	}

	@Override
	public void setMinimumRow(int minimumRow) {
		int oldValue = this.minimumRow;
		this.minimumRow = minimumRow;
		propertySupport.firePropertyChange(MINIMUM_ROW_PROPERTY.toString(),
				oldValue, rowIndex);
	}

	@Override
	public int getMinimumRow() {
		return minimumRow;
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount() {
		return TYPES.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case ROW_INDEX_COLUMN:
			return rowIndex;
		case MAXIMUM_ROW_COLUMN:
			return maximumRow;
		case COLUMN_INDEX_COLUMN:
			return columnIndex;
		case MAXIMUM_COLUMN_COLUMN:
			return maximumColumn;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		int index = (Integer) value;
		switch (column) {
		case ROW_INDEX_COLUMN:
			setRowIndex(index);
			break;
		case COLUMN_INDEX_COLUMN:
			setColumnIndex(index);
			break;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return EDITABLE[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return TYPES[columnIndex];
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(Object propertyName,
			PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(propertyName.toString(),
				listener);
	}

	@Override
	public void removePropertyChangeListener(Object propertyName,
			PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(propertyName.toString(),
				listener);
	}

}
