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
package com.anrisoftware.prefdialog.miscswing.multichart.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

public interface ChartModel extends Serializable {

	/**
	 * Chart model property.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 3.0
	 */
	public enum ChartModelProperty {

		/**
		 * The minimum range value of the chart property.
		 * 
		 * @see ChartModel#setValuesMinimum(double)
		 */
		VALUES_MINIMUM,

		/**
		 * The maximum range value of the chart property.
		 * 
		 * @see ChartModel#setValuesMaximum(double)
		 */
		VALUES_MAXIMUM
	}

	int getRowCount();

	int getColumnCount();

	String getColumnName(int column);

	double getValueAt(int row, int column);

	/**
	 * Sets the offset of the data that is shown in the graph.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param offset
	 *            the offset.
	 */
	@OnAwt
	void setOffset(int offset);

	/**
	 * Returns the offset of the data that is shown in the graph.
	 * 
	 * @return the offset.
	 */
	int getOffset();

	/**
	 * Sets the maximum visible data points of the graph.
	 * 
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param max
	 *            the maximum.
	 */
	@OnAwt
	void setViewMaximum(int max);

	/**
	 * Returns the maximum visible data points of the graph.
	 * 
	 * @returns the maximum.
	 */
	int getViewMaximum();

	/**
	 * Returns the count of values in the model that can be shown in the chart.
	 * 
	 * @return the count of values.
	 */
	int getMaximumRowCount();

	/**
	 * Returns the row index from the chart offset.
	 * 
	 * @param row
	 *            the row index.
	 * 
	 * @return the row index from the chart offset.
	 */
	int toChartRow(int row);

	/**
	 * Sets the maximum range value of the chart.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link ChartModelProperty#VALUES_MAXIMUM} property name.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param max
	 *            the maximum value.
	 */
	@OnAwt
	void setValuesMaximum(double max);

	double getValuesMaximum();

	/**
	 * Sets the minimum range value of the chart.
	 * <p>
	 * <h2>Property</h2>
	 * <p>
	 * Notifies the property change listeners with the
	 * {@link ChartModelProperty#VALUES_MINIMUM} property name.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param min
	 *            the minimum value.
	 */
	@OnAwt
	void setValuesMinimum(double min);

	double getValuesMinimum();

	void addChartModelListener(ChartModelListener l);

	void removeChartModelListener(ChartModelListener l);

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see ChartModelProperty
	 */
	void addPropertyChangeListener(ChartModelProperty property,
			PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see ChartModelProperty
	 */
	void removePropertyChangeListener(ChartModelProperty property,
			PropertyChangeListener listener);

}
