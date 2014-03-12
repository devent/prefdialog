package com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel;

import com.anrisoftware.globalpom.data.DataBean;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ColumnNames;

/**
 * Factory to create the chart model for data bean.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface DataBeanChartModelFactory {

    /**
     * Creates the chart model for the data.
     * 
     * @param data
     *            the {@link DataBean} data.
     * 
     * @param columnNames
     *            the column names.
     * 
     * @return the {@link DataBeanChartModel}.
     */
    DataBeanChartModel create(DataBean data, String[] columnNames);

    /**
     * Creates the chart model for the data.
     * 
     * @param data
     *            the {@link DataBean} data.
     * 
     * @param columnNames
     *            the column names {@link ColumnNames}.
     * 
     * @return the {@link DataBeanChartModel}.
     */
    DataBeanChartModel create(DataBean data, ColumnNames columnNames);
}
