package com.anrisoftware.prefdialog.miscswing.chart.datamodel;

import javax.inject.Inject;

import com.anrisoftware.globalpom.data.DataBean;
import com.anrisoftware.prefdialog.miscswing.chart.model.AbstractChartModel;
import com.google.inject.assistedinject.Assisted;

/**
 * Chart model for data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DataChartModel extends AbstractChartModel {

	private final DataBean data;

	private final String[] columnNames;

	/**
	 * @see DataChartModelFactory#create(DataBean, String[])
	 */
	@Inject
	DataChartModel(@Assisted DataBean data, @Assisted String[] columnNames) {
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
		if (column >= columnNames.length) {
			return null;
		} else {
			return columnNames[column];
		}
	}

	@Override
	public double getValueAt(int row, int column) {
		return data.unsafe_get(row, column);
	}

}
