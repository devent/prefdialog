package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel;

/**
 * Factory to create the scrolling model for the chart.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
interface GraphScrollModelFactory {

	/**
	 * Creates the scrolling model for the chart with the specified chart model.
	 * 
	 * @param model
	 *            the {@link ChartModel}.
	 * 
	 * @return the {@link GraphScrollModel}.
	 */
	GraphScrollModel create(ChartModel model);
}
