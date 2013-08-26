/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.COLUMN_INDEX_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.ROW_INDEX_PROPERTY;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Returns the row and column index, the maximum rows and columns.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class NavigationModelImpl implements NavigationModel {

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
		propertySupport.firePropertyChange(COLUMN_INDEX_PROPERTY.toString(),
				oldValue, columnIndex);
	}

	@Override
	public int getColumnIndex() {
		return columnIndex;
	}

	@Override
	public void setMaximumColumn(int maximumColumn) {
		int oldValue = this.maximumColumn;
		this.maximumColumn = maximumColumn;
		propertySupport.firePropertyChange(MAXIMUM_COLUMN_PROPERTY.toString(),
				oldValue, maximumColumn);
	}

	@Override
	public int getMaximumColumn() {
		return maximumColumn;
	}

	@Override
	public void setMaximumRow(int maximumRow) {
		int oldValue = this.maximumRow;
		this.maximumRow = maximumRow;
		propertySupport.firePropertyChange(MAXIMUM_ROW_PROPERTY.toString(),
				oldValue, maximumRow);
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
