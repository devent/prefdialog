package com.anrisoftware.prefdialog.miscswing.chart.datamodel;

import com.anrisoftware.globalpom.data.DataBean;
import com.anrisoftware.prefdialog.miscswing.chart.columnnames.DefaultColumnNamesFactory;
import com.anrisoftware.prefdialog.miscswing.chart.model.AbstractChartModel;
import com.anrisoftware.prefdialog.miscswing.chart.model.ColumnNames;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Chart model for data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DataChartModel extends AbstractChartModel {

	private final DataBean data;

	private final ColumnNames columnNames;

	/**
	 * @see DataChartModelFactory#create(DataBean, String[])
	 */
	@AssistedInject
	DataChartModel(DefaultColumnNamesFactory namesFactory,
			@Assisted DataBean data, @Assisted String[] columnNames) {
		this.data = data;
		this.columnNames = namesFactory.create(columnNames);
	}

	/**
	 * @see DataChartModelFactory#create(DataBean, ColumnNames)
	 */
	@AssistedInject
	DataChartModel(@Assisted DataBean data, @Assisted ColumnNames columnNames) {
		this.data = data;
		this.columnNames = columnNames;
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

}
