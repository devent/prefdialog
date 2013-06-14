package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import static com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.ViewRange.MAXIMUM_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.ViewRange.OFFSET_PROPERTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.inject.Inject;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public class SpreadsheetTableModel extends AbstractTableModel {

	private final SpreadsheetTableModelLogger log;

	private final PropertyChangeListener offsetListener;

	private final PropertyChangeListener maximumListener;

	private final TableModel model;

	private ViewRange range;

	@Inject
	SpreadsheetTableModel(SpreadsheetTableModelLogger logger,
			@Assisted TableModel model, @Assisted ViewRange range) {
		this.log = logger;
		this.model = model;
		this.offsetListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				fireTableDataChanged();
			}
		};
		this.maximumListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				int old = (Integer) evt.getOldValue();
				int value = (Integer) evt.getNewValue();
				if (old < value) {
					fireTableRowsInserted(old, value);
				} else {
					fireTableRowsDeleted(value, old);
				}
			}
		};
		setViewRange(range);
	}

	public void setViewRange(ViewRange range) {
		log.checkViewRange(range);
		removeOldRange(this.range);
		this.range = range;
		range.addPropertyChangeListener(OFFSET_PROPERTY, offsetListener);
		range.addPropertyChangeListener(MAXIMUM_PROPERTY, maximumListener);
	}

	private void removeOldRange(ViewRange range) {
		if (range != null) {
			range.removePropertyChangeListener(OFFSET_PROPERTY, offsetListener);
			range.removePropertyChangeListener(MAXIMUM_PROPERTY,
					maximumListener);
		}
	}

	public ViewRange getViewRange() {
		return range;
	}

	public int getOffset() {
		return range.getOffset();
	}

	public int getMaximum() {
		return range.getMaximum();
	}

	@Override
	public int getRowCount() {
		return range.getMaximum();
	}

	@Override
	public int getColumnCount() {
		return model.getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return model.getColumnName(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return model.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return model.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return model.getValueAt(getOffset() + rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		model.setValueAt(aValue, getOffset() + rowIndex, columnIndex);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		model.addTableModelListener(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		model.removeTableModelListener(l);
	}

}
