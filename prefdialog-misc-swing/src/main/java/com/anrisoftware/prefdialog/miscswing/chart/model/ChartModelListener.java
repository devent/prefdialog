package com.anrisoftware.prefdialog.miscswing.chart.model;

import java.util.EventListener;

/**
 * Notifies that the chart data have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ChartModelListener extends EventListener {

	/**
	 * Called when the chart data have been changed.
	 * 
	 * @param e
	 *            the listener {@link ChartModelEvent} event.
	 */
	void chartChanged(ChartModelEvent e);
}
