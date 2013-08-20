package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface NavigationModel {

	/**
	 * Properties of the navigation model.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	public static enum Property {

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setRowIndex(int)
		 */
		ROW_INDEX_PROPERTY,

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setColumnIndex(int)
		 */
		COLUMN_INDEX_PROPERTY,

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setMaximumColumn(int)
		 */
		MAXIMUM_COLUMN_PROPERTY,

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setMaximumRow(int)
		 */
		MAXIMUM_ROW_PROPERTY,

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setMinimumColumn(int)
		 */
		MINIMUM_COLUMN_PROPERTY,

		/**
		 * The current row index.
		 * 
		 * @see NavigationModel#setMinimumRow(int)
		 */
		MINIMUM_ROW_PROPERTY
	}

	/**
	 * Sets the current row index.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link Property#ROW_INDEX_PROPERTY} property.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param rowIndex
	 *            the row index.
	 */
	void setRowIndex(int rowIndex);

	int getRowIndex();

	/**
	 * Sets the current column index.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link Property#COLUMN_INDEX_PROPERTY} property.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param columnIndex
	 *            the column index.
	 */
	void setColumnIndex(int columnIndex);

	int getColumnIndex();

	/**
	 * Sets the maximum column index.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link Property#MAXIMUM_COLUMN_PROPERTY} property.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param maximumColumn
	 *            the maximum column index.
	 */
	void setMaximumColumn(int maximumColumn);

	int getMaximumColumn();

	/**
	 * Sets the maximum row index.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link Property#MAXIMUM_ROW_PROPERTY} property.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param maximumColumn
	 *            the maximum row index.
	 */
	void setMaximumRow(int maximumRow);

	int getMaximumRow();

	void setMinimumColumn(int minimumColumn);

	int getMinimumColumn();

	void setMinimumRow(int minimumRow);

	int getMinimumRow();

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see Property#COLUMN_INDEX_PROPERTY
	 * @see Property#ROW_INDEX_PROPERTY
	 * @see Property#MAXIMUM_COLUMN_PROPERTY
	 * @see Property#MAXIMUM_ROW_PROPERTY
	 * @see Property#MINIMUM_COLUMN_PROPERTY
	 * @see Property#MINIMUM_ROW_PROPERTY
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see Property#COLUMN_INDEX_PROPERTY
	 * @see Property#ROW_INDEX_PROPERTY
	 * @see Property#MAXIMUM_COLUMN_PROPERTY
	 * @see Property#MAXIMUM_ROW_PROPERTY
	 * @see Property#MINIMUM_COLUMN_PROPERTY
	 * @see Property#MINIMUM_ROW_PROPERTY
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see Property#COLUMN_INDEX_PROPERTY
	 * @see Property#ROW_INDEX_PROPERTY
	 * @see Property#MAXIMUM_COLUMN_PROPERTY
	 * @see Property#MAXIMUM_ROW_PROPERTY
	 * @see Property#MINIMUM_COLUMN_PROPERTY
	 * @see Property#MINIMUM_ROW_PROPERTY
	 */
	void addPropertyChangeListener(Object propertyName,
			PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see Property#COLUMN_INDEX_PROPERTY
	 * @see Property#ROW_INDEX_PROPERTY
	 * @see Property#MAXIMUM_COLUMN_PROPERTY
	 * @see Property#MAXIMUM_ROW_PROPERTY
	 * @see Property#MINIMUM_COLUMN_PROPERTY
	 * @see Property#MINIMUM_ROW_PROPERTY
	 */
	void removePropertyChangeListener(Object propertyName,
			PropertyChangeListener listener);

}