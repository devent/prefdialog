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
public final class DataBeanChartModel extends AbstractChartModel {

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
