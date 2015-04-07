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
package com.anrisoftware.prefdialog.miscswing.multichart.model;

import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel.ChartModelProperty.VALUES_MAXIMUM;
import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel.ChartModelProperty.VALUES_MINIMUM;
import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent.ALL_COLUMNS;
import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent.EventType.DELETED;
import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent.EventType.INSERTED;
import static com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent.EventType.UPDATED;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Informs chart model listeners of data change.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractChartModel implements ChartModel {

	private transient EventListenerSupport<ChartModelListener> s;

	private transient PropertyChangeSupport p;

	private int offset;

	private int viewMaximum;

	private double valuesMinimum;

	private double valuesMaximum;

	protected AbstractChartModel() {
		this.offset = 0;
		this.viewMaximum = 100;
		this.valuesMinimum = -100;
		this.valuesMaximum = 100;
		readResolve();
	}

	private Object readResolve() {
		this.s = new EventListenerSupport<ChartModelListener>(
				ChartModelListener.class);
		this.p = new PropertyChangeSupport(this);
		return this;
	}

	@Override
	@OnAwt
	public void setOffset(int offset) {
		if (offset < 0 || offset + getMaximumRowCount() > getRowCount()) {
			return;
		}
		int oldValue = this.offset;
		if (oldValue == offset) {
			return;
		}
		this.offset = offset;
		fireRowsUpdated(0, getMaximumRowCount() - 1);
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setViewMaximum(int max) {
		int oldMax = getMaximumRowCount();
		int oldValue = this.viewMaximum;
		this.viewMaximum = max;
		if (oldValue == max) {
			return;
		}
		int newMax = getMaximumRowCount();
		if (oldMax - newMax > 0) {
			fireRowsDeleted(newMax, oldMax - 1);
		} else {
			fireRowsInserted(oldMax, newMax - 1);
		}
	}

	@Override
	public int getViewMaximum() {
		return viewMaximum;
	}

	@Override
	public void setValuesMaximum(double max) {
		double oldValue = this.valuesMaximum;
		this.valuesMaximum = max;
		p.firePropertyChange(VALUES_MAXIMUM.toString(), oldValue, max);
	}

	@Override
	public double getValuesMaximum() {
		return valuesMaximum;
	}

	@Override
	public void setValuesMinimum(double min) {
		double oldValue = this.valuesMinimum;
		this.valuesMinimum = min;
		p.firePropertyChange(VALUES_MINIMUM.toString(), oldValue, min);
	}

	@Override
	public double getValuesMinimum() {
		return valuesMinimum;
	}

	@Override
	public final int getMaximumRowCount() {
		return min(viewMaximum, getRowCount());
	}

	@Override
	public final int toChartRow(int row) {
		return row + offset;
	}

	public void fireTableDataChanged() {
		fireChartChanged(new ChartModelEvent(this, UPDATED, 0, MAX_VALUE,
				ALL_COLUMNS, getOffset()));
	}

	public void fireRowsInserted(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, INSERTED, firstRow, lastRow,
				ALL_COLUMNS, getOffset()));
	}

	public void fireRowsUpdated(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, UPDATED, firstRow, lastRow,
				ALL_COLUMNS, getOffset()));
	}

	public void fireRowsDeleted(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, DELETED, firstRow, lastRow,
				ALL_COLUMNS, getOffset()));
	}

	public void fireChartValueUpdated(int row, int column) {
		fireChartChanged(new ChartModelEvent(this, UPDATED, row, row, column,
				getOffset()));
	}

	public void fireChartChanged(ChartModelEvent e) {
		s.fire().chartChanged(e);
	}

	@Override
	public void addChartModelListener(ChartModelListener l) {
		s.addListener(l);
	}

	@Override
	public void removeChartModelListener(ChartModelListener l) {
		s.removeListener(l);
	}

	@Override
	public void addPropertyChangeListener(ChartModelProperty property,
			PropertyChangeListener listener) {
		p.addPropertyChangeListener(property.toString(), listener);
	}

	@Override
	public void removePropertyChangeListener(ChartModelProperty property,
			PropertyChangeListener listener) {
		p.removePropertyChangeListener(property.toString(), listener);
	}

}
