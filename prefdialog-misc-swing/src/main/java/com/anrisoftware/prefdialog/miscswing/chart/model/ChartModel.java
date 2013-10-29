package com.anrisoftware.prefdialog.miscswing.chart.model;

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
	void setMaximum(int max);

	/**
	 * Returns the maximum visible data points of the graph.
	 * 
	 * @returns the maximum.
	 */
	int getMaximum();

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
