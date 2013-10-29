package com.anrisoftware.prefdialog.miscswing.chart.model;

import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel.ChartModelProperty.VALUES_MAXIMUM;
import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel.ChartModelProperty.VALUES_MINIMUM;
import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent.ALL_COLUMNS;
import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent.EventType.DELETED;
import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent.EventType.INSERTED;
import static com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent.EventType.UPDATED;
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

	private int maximum;

	private double valuesMinimum;

	private double valuesMaximum;

	protected AbstractChartModel() {
		this.offset = 0;
		this.maximum = 100;
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
	public void setMaximum(int max) {
		int oldMax = getMaximumRowCount();
		int oldValue = this.maximum;
		this.maximum = max;
		if (oldValue == max) {
			return;
		}
		int newMax = getMaximumRowCount();
		if (oldMax - newMax > 0) {
			fireRowsDeleted(newMax - 1, oldMax - 1);
		} else {
			fireRowsInserted(oldMax - 1, newMax - 1);
		}
	}

	@Override
	public int getMaximum() {
		return maximum;
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
		return min(maximum, getRowCount());
	}

	@Override
	public final int toChartRow(int row) {
		return row + offset;
	}

	public void fireTableDataChanged() {
		fireChartChanged(new ChartModelEvent(this, UPDATED, 0, MAX_VALUE,
				ALL_COLUMNS));
	}

	public void fireRowsInserted(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, INSERTED, firstRow, lastRow,
				ALL_COLUMNS));
	}

	public void fireRowsUpdated(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, UPDATED, firstRow, lastRow,
				ALL_COLUMNS));
	}

	public void fireRowsDeleted(int firstRow, int lastRow) {
		fireChartChanged(new ChartModelEvent(this, DELETED, firstRow, lastRow,
				ALL_COLUMNS));
	}

	public void fireChartValueUpdated(int row, int column) {
		fireChartChanged(new ChartModelEvent(this, UPDATED, row, row, column));
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
