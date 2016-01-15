/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.anrisoftware.globalpom.data.DataBean;
import com.anrisoftware.globalpom.data.DataProperty;
import com.anrisoftware.globalpom.properties.ListPropertyChangeEvent;
import com.anrisoftware.prefdialog.miscswing.multichart.columnnames.DefaultColumnNamesFactory;
import com.anrisoftware.prefdialog.miscswing.multichart.model.AbstractChartModel;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ColumnNames;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Chart model for data bean.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class DataBeanChartModel extends AbstractChartModel {

    private final DataBean data;

    private final ColumnNames columnNames;

    private PropertyChangeListener dataListener;

    /**
     * @see DataBeanChartModelFactory#create(DataBean, String[])
     */
    @AssistedInject
    DataBeanChartModel(DefaultColumnNamesFactory namesFactory,
            @Assisted DataBean data, @Assisted String[] columnNames) {
        this.data = data;
        this.columnNames = namesFactory.create(columnNames);
        readResolve();
    }

    /**
     * @see DataBeanChartModelFactory#create(DataBean, ColumnNames)
     */
    @AssistedInject
    DataBeanChartModel(@Assisted DataBean data,
            @Assisted ColumnNames columnNames) {
        this.data = data;
        this.columnNames = columnNames;
        readResolve();
    }

    public Object readResolve() {
        this.dataListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateData((ListPropertyChangeEvent) evt);
            }
        };
        data.addPropertyChangeListener(DataProperty.DATA, dataListener);
        return this;
    }

    @Override
    public int getRowCount() {
        return data.getNumRows();
    }

    @Override
    public int getColumnCount() {
        return data.getNumCols();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.getColumnName(column);
    }

    @Override
    public double getValueAt(int row, int column) {
        return data.unsafe_get(row, column);
    }

    private void updateData(ListPropertyChangeEvent evt) {
        int index0 = evt.getIndex0();
        int index1 = evt.getIndex1();
        switch (evt.getType()) {
        case ADDED:
            fireRowsInserted(index0, index1);
            break;
        case CHANGED:
            fireRowsUpdated(index0, index1);
            break;
        case REMOVED:
            fireRowsDeleted(index0, index1);
            break;
        }
    }

}
