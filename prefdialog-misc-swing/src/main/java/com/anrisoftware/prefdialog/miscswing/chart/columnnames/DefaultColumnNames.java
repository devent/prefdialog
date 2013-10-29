package com.anrisoftware.prefdialog.miscswing.chart.columnnames;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.chart.model.ColumnNames;
import com.google.inject.assistedinject.Assisted;

public class DefaultColumnNames implements ColumnNames {

	private final String[] names;

	@Inject
	DefaultColumnNames(@Assisted String[] names) {
		this.names = names;
	}

	@Override
	public String getColumnName(int column) {
		return column < names.length ? names[column] : null;
	}

}
