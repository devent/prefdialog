package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener.lockedVetoableChangeListener;
import static com.google.inject.Guice.createInjector;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.INSERT;
import static javax.swing.event.TableModelEvent.UPDATE;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener;
import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.SpreadsheetTable;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Decorates the spreadsheet with a navigation panel. The navigation panel will
 * show the position in the table as the selected column and row. The user can
 * enter a new position.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SpreadsheetNavigationPanel {

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, JScrollPane,
	 *      SpreadsheetTable)
	 */
	public static SpreadsheetNavigationPanel decorate(JPanel container,
			JScrollPane pane, SpreadsheetTable table) {
		Injector injector = createInjector(new SpreadsheetNavigationPanelModule());
		return injector.getInstance(SpreadsheetNavigationPanelFactory.class)
				.create(container, pane, table);
	}

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(SpreadsheetTable)
	 */
	public static SpreadsheetNavigationPanel decorate(SpreadsheetTable table) {
		Injector injector = createInjector(new SpreadsheetNavigationPanelModule());
		return injector.getInstance(SpreadsheetNavigationPanelFactory.class)
				.create(table);
	}

	private final JScrollPane scrollPane;

	private final UiDataPanel dataPanel;

	private final SpreadsheetTable table;

	private final JPanel container;

	private final TableModelListener tableListener;

	private final LockedVetoableChangeListener currentColumnListener;

	private final LockedVetoableChangeListener currentRowListener;

	private ListSelectionListener tableSelectionListener;

	private int startRowIndex;

	private int startColumnIndex;

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(SpreadsheetTable)
	 */
	@AssistedInject
	SpreadsheetNavigationPanel(UiDataPanel dataPanel,
			@Assisted SpreadsheetTable table) {
		this(dataPanel, new JPanel(), new JScrollPane(), table);
	}

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, JScrollPane,
	 *      SpreadsheetTable)
	 */
	@AssistedInject
	SpreadsheetNavigationPanel(UiDataPanel dataPanel,
			@Assisted JPanel container, @Assisted JScrollPane pane,
			@Assisted SpreadsheetTable table) {
		this.container = container;
		this.scrollPane = pane;
		this.dataPanel = dataPanel;
		this.table = table;
		this.startRowIndex = 1;
		this.startColumnIndex = 1;
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
		this.currentColumnListener = lockedVetoableChangeListener(new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				validateColumn((Integer) evt.getNewValue(), evt);
				updateSelectedColumn((Integer) evt.getNewValue());
			}
		});
		this.currentRowListener = lockedVetoableChangeListener(new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				validateRow((Integer) evt.getNewValue(), evt);
				updateSelectedRow((Integer) evt.getNewValue());
			}
		});
		this.tableSelectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				updateSelected();
			}
		};
		setupPane();
		setupTable();
		setupDataPanel();
	}

	protected void updateSelected() {
		currentColumnListener.lock();
		currentRowListener.lock();
		int row = getSelectedRow();
		int column = getSelectedColumn();
		dataPanel.getCurrentRowValidatingField().setValue(row);
		dataPanel.getCurrentColumnValidatingField().setValue(column);
		currentColumnListener.unlock();
		currentRowListener.unlock();
	}

	private void updateSelectedColumn(int column) {
		column = column - startColumnIndex;
		JTable table = getViewTable();
		table.setColumnSelectionInterval(column, column);
		table.scrollRectToVisible(table.getCellRect(0, column, true));
	}

	private void validateColumn(int column, PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (column < startColumnIndex) {
			throw new PropertyVetoException("", evt);
		}
		if (column >= getColumnCount()) {
			throw new PropertyVetoException("", evt);
		}
	}

	private void updateSelectedRow(int row) {
		row = row - getViewOffset() - startRowIndex;
		JTable table = getViewTable();
		table.setRowSelectionInterval(row, row);
		table.scrollRectToVisible(table.getCellRect(row, 0, true));
	}

	private void validateRow(int row, PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (row < startRowIndex) {
			throw new PropertyVetoException("", evt);
		}
		if (row >= getViewRowCount()) {
			setViewMaximum(row + 1);
		}
	}

	private void setupPane() {
		container.setLayout(new BorderLayout());
		container.add(scrollPane, CENTER);
	}

	private void setupTable() {
		scrollPane.setViewportView(getViewTable());
	}

	private void setupDataPanel() {
		container.add(dataPanel, SOUTH);
		getViewModel().addTableModelListener(tableListener);
		dataPanel.getCurrentColumnValidatingField().addVetoableChangeListener(
				currentColumnListener);
		dataPanel.getCurrentRowValidatingField().addVetoableChangeListener(
				currentRowListener);
		getViewTable().getSelectionModel().addListSelectionListener(
				tableSelectionListener);
		getViewTable().getColumnModel().getSelectionModel()
				.addListSelectionListener(tableSelectionListener);
		updateSelected();
		updateMaximum();
	}

	private void updateMaximum() {
		dataPanel.getMaximumColumnField().setValue(getColumnCount());
		dataPanel.getMaximumRowField().setValue(getRowCount());
	}

	private int getViewRowCount() {
		return table.getModel().getRowCount();
	}

	private int getRowCount() {
		return getViewModel().getRowCount();
	}

	private int getColumnCount() {
		return getViewModel().getColumnCount();
	}

	private int getViewOffset() {
		return table.getOffset();
	}

	private void setViewMaximum(int maximum) {
		table.setMaximum(maximum);
	}

	private int getSelectedColumn() {
		return getViewTable().getColumnModel().getSelectionModel()
				.getMaxSelectionIndex()
				+ startColumnIndex;
	}

	private int getSelectedRow() {
		return getViewTable().getSelectionModel().getMaxSelectionIndex()
				+ getViewOffset() + startRowIndex;
	}

	private JTable getViewTable() {
		return table.getTable();
	}

	private TableModel getViewModel() {
		return table.getModel().getModel();
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
	 * Returns the scroll pane of the spreadsheet table.
	 * 
	 * @return the {@link JScrollPane}.
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
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
	 * Sets the start index for row indices. The start index is used to identify
	 * the first row.
	 * 
	 * @param startIndex
	 *            the start index.
	 */
	public void setStartRowIndex(int startIndex) {
		this.startRowIndex = startIndex;
	}

	/**
	 * Returns the start index for row and column indices.
	 * 
	 * @return the start index.
	 */
	public int getStartRowIndex() {
		return startRowIndex;
	}

	/**
	 * Sets the start index for column indices. The start index is used to
	 * identify the first column.
	 * 
	 * @param startIndex
	 *            the start index.
	 */
	public void setStartColumnIndex(int startIndex) {
		this.startColumnIndex = startIndex;
	}

	/**
	 * Returns the start index for row and column indices.
	 * 
	 * @return the start index.
	 */
	public int getStartColumnIndex() {
		return startColumnIndex;
	}
}
