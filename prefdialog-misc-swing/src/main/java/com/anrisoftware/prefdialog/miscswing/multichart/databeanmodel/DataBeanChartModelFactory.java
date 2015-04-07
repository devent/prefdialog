/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel;

import com.anrisoftware.globalpom.data.DataBean;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;
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
     * @return the {@link ChartModel}.
     */
    ChartModel create(DataBean data, String[] columnNames);

    /**
     * Creates the chart model for the data.
     * 
     * @param data
     *            the {@link DataBean} data.
     * 
     * @param columnNames
     *            the column names {@link ColumnNames}.
     * 
     * @return the {@link ChartModel}.
     */
    ChartModel create(DataBean data, ColumnNames columnNames);
}
