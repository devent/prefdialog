/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener.lockedChangeListener;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.COLUMN_INDEX_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.ROW_INDEX_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationPanelModule.getFactory;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.INSERT;
import static javax.swing.event.TableModelEvent.UPDATE;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener;
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTable;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Decorates the spreadsheet with a navigation panel. The navigation panel will
 * show the position in the table as the selected column and row. The user can
 * enter a new position.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class NavigationPanel {

	/**
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @see NavigationPanelFactory#create(JPanel, SpreadsheetTable)
	 */
	public static NavigationPanel decorate(JPanel container,
			SpreadsheetTable table) {
		return getFactory().create(container, table);
	}

	private UiNavigationPanel dataPanel;

	private final SpreadsheetTable table;

	private final JPanel container;

	private final TableModelListener tableListener;

	private final ListSelectionListener tableSelectionListener;

	private final LockedChangeListener columnIndexListener;

	private final LockedChangeListener rowIndexListener;

	private final PropertyChangeListener maximumRowListener;

	private final PropertyChangeListener maximumColumnListener;

	private NavigationModelImpl navigationModel;

	/**
	 * @see NavigationPanelFactory#create(JPanel, SpreadsheetTablee)
	 */
	@AssistedInject
	NavigationPanel(@Assisted JPanel container, @Assisted SpreadsheetTable table) {
		this.container = container;
		this.table = table;
		this.tableListener = new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				switch (e.getType()) {
				case INSERT:
				case DELETE:
				case UPDATE:
					updateMaximum();
					break;
				}
			}
		};
		this.tableSelectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				updateSelected();
			}
		};
		this.columnIndexListener = lockedChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateSelectedColumn((Integer) evt.getNewValue());
			}
		});
		this.rowIndexListener = lockedChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateSelectedRow((Integer) evt.getNewValue());
			}
		});
		this.maximumRowListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dataPanel.getMaximumRow().setValue(evt.getNewValue());
			}
		};
		this.maximumColumnListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dataPanel.getMaximumColumn().setValue(evt.getNewValue());
			}
		};
	}

	@Inject
	void setNavigationPanel(UiNavigationPanel dataPanel,
			NavigationModelImpl navigationModel) {
		this.dataPanel = dataPanel;
		this.navigationModel = navigationModel;
		setupContainer();
		setupDataTable();
		setupNavigationPanel();
	}

	private void setupContainer() {
		container.setLayout(new BorderLayout());
		container.add(dataPanel, BorderLayout.CENTER);
	}

	private void setupDataTable() {
		JTable table = this.table.getTable();
		table.getModel().addTableModelListener(tableListener);
		table.getSelectionModel().addListSelectionListener(
				tableSelectionListener);
		table.getColumnModel().getSelectionModel()
				.addListSelectionListener(tableSelectionListener);
	}

	private void setupNavigationPanel() {
		container.add(dataPanel, SOUTH);
		navigationModel.addPropertyChangeListener(COLUMN_INDEX_PROPERTY,
				columnIndexListener);
		navigationModel.addPropertyChangeListener(ROW_INDEX_PROPERTY,
				rowIndexListener);
		navigationModel.addPropertyChangeListener(MAXIMUM_COLUMN_PROPERTY,
				maximumColumnListener);
		navigationModel.addPropertyChangeListener(MAXIMUM_ROW_PROPERTY,
				maximumRowListener);
		dataPanel.getCurrentRowModel().setNavigation(navigationModel);
		dataPanel.getCurrentColumnModel().setNavigation(navigationModel);
		updateSelected();
		updateMaximum();
	}

	private void updateMaximum() {
		int maximumColumn = getColumnCount() - 1;
		int maximumRow = getRowCount() - 1;
		int column = getColumnIndex();
		int row = getRowIndex();
		if (column > maximumColumn) {
			setColumnIndex(maximumColumn);
		}
		if (row > maximumRow) {
			setRowIndex(maximumRow);
		}
		setMaximumColumn(maximumColumn);
		setMaximumRow(maximumRow);
	}

	private void updateSelected() {
		int row = getSelectedRow1();
		int column = getSelectedColumn1();
		columnIndexListener.lock();
		rowIndexListener.lock();
		setRowIndex(row);
		setColumnIndex(column);
		columnIndexListener.unlock();
		rowIndexListener.unlock();
	}

	private void updateSelectedColumn(int column) {
		column = column - getMinimumColumn();
		JTable table = getDataTable();
		table.setColumnSelectionInterval(column, column);
		table.scrollRectToVisible(table.getCellRect(0, column, true));
	}

	private void updateSelectedRow(int row) {
		row = row - getMinimumRow();
		JTable table = getDataTable();
		table.setRowSelectionInterval(row, row);
		table.scrollRectToVisible(table.getCellRect(row, 0, true));
	}

	private int getSelectedColumn1() {
		return getDataTable().getColumnModel().getSelectionModel()
				.getLeadSelectionIndex();
	}

	private int getSelectedRow1() {
		return getDataTable().getSelectionModel().getLeadSelectionIndex();
	}

	private int getRowCount() {
		return getDataTable().getModel().getRowCount();
	}

	private int getColumnCount() {
		return getDataTable().getModel().getColumnCount();
	}

	private JTable getDataTable() {
		return table.getTable();
	}

	/**
	 * Returns the container of the spreadsheet navigation.
	 * 
	 * @return the {@link JPanel}.
	 */
	public JPanel getContainer() {
		return container;
	}

	/**
	 * Returns the spreadsheet table of the panel.
	 * 
	 * @return the {@link SpreadsheetTable}.
	 */
	public SpreadsheetTable getTable() {
		return table;
	}

	/**
	 * Returns the navigation model.
	 * 
	 * @return the {@link NavigationModel}.
	 */
	public NavigationModel getNavigationModel() {
		return navigationModel;
	}

	/**
	 * @see NavigationModel#setRowIndex(int)
	 */
	public void setRowIndex(int rowIndex) {
		navigationModel.setRowIndex(rowIndex);
	}

	/**
	 * @see NavigationModel#getRowIndex()
	 */
	public int getRowIndex() {
		return navigationModel.getRowIndex();
	}

	/**
	 * @see NavigationModel#setColumnIndex(int)
	 */
	public void setColumnIndex(int columnIndex) {
		navigationModel.setColumnIndex(columnIndex);
	}

	/**
	 * @see NavigationModel#getColumnIndex()
	 */
	public int getColumnIndex() {
		return navigationModel.getColumnIndex();
	}

	/**
	 * @see NavigationModel#setMaximumColumn(int)
	 */
	public void setMaximumColumn(int maximumColumn) {
		navigationModel.setMaximumColumn(maximumColumn);
	}

	/**
	 * @see NavigationModel#getMaximumColumn()
	 */
	public int getMaximumColumn() {
		return navigationModel.getMaximumColumn();
	}

	/**
	 * @see NavigationModel#setMaximumRow(int)
	 */
	public void setMaximumRow(int maximumRow) {
		navigationModel.setMaximumRow(maximumRow);
	}

	/**
	 * @see NavigationModel#getMaximumRow()
	 */
	public int getMaximumRow() {
		return navigationModel.getMaximumRow();
	}

	/**
	 * @see NavigationModel#setMinimumColumn(int)
	 */
	public void setMinimumColumn(int minimumColumn) {
		navigationModel.setMinimumColumn(minimumColumn);
	}

	/**
	 * @see NavigationModel#getMinimumColumn()
	 */
	public int getMinimumColumn() {
		return navigationModel.getMinimumColumn();
	}

	/**
	 * @see NavigationModel#setMinimumRow(int)
	 */
	public void setMinimumRow(int minimumRow) {
		navigationModel.setMinimumRow(minimumRow);
	}

	/**
	 * @see NavigationModel#getMinimumRow()
	 */
	public int getMinimumRow() {
		return navigationModel.getMinimumRow();
	}

}
