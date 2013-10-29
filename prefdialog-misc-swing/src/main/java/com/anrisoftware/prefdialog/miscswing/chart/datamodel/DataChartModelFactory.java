package com.anrisoftware.prefdialog.miscswing.chart.datamodel;

import com.anrisoftware.globalpom.data.DataBean;

/**
 * Factory to create the chart model for data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface DataChartModelFactory {

	/**
	 * Creates the chart model for the data.
	 * 
	 * @param data
	 *            the {@link DataBean} data.
	 * 
	 * @param columnNames
	 *            the column names.
	 * 
	 * @return the {@link DataChartModel}.
	 */
	DataChartModel create(DataBean data, String[] columnNames);
}
