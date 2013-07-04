package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation;

import static com.google.inject.Guice.createInjector;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.INSERT;
import static javax.swing.event.TableModelEvent.UPDATE;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
 * @since 3.0
 */
public class SpreadsheetNavigationPanel {

	private static final String VALUE_PROPERTY = "value";

	private static SpreadsheetNavigationPanelFactory factory;

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, SpreadsheetTable,
	 *      JScrollPane)
	 */
	public static SpreadsheetNavigationPanel decorate(JPanel container,
			SpreadsheetTable table, JScrollPane pane) {
		return getFactory().create(container, table, pane);
	}

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, SpreadsheetTable)
	 */
	public static SpreadsheetNavigationPanel decorate(JPanel container,
			SpreadsheetTable table) {
		return getFactory().create(container, table);
	}

	private static SpreadsheetNavigationPanelFactory getFactory() {
		if (factory == null) {
			Injector injector = createInjector(new SpreadsheetNavigationPanelModule());
			factory = injector
					.getInstance(SpreadsheetNavigationPanelFactory.class);
		}
		return factory;
	}

	private final JScrollPane scrollPane;

	private final UiDataPanel dataPanel;

	private final SpreadsheetTable table;

	private final JPanel container;

	private final TableModelListener tableListener;

	private final InputVerifier currentColumnVerifier;

	private final PropertyChangeListener currentRowListener;

	private final ListSelectionListener tableSelectionListener;

	private int startRowIndex;

	private int startColumnIndex;

	private PropertyChangeListener currentColumnListener;

	private InputVerifier currentRowVerifier;

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, SpreadsheetTable)
	 */
	@AssistedInject
	SpreadsheetNavigationPanel(UiDataPanel dataPanel,
			@Assisted JPanel container, @Assisted SpreadsheetTable table) {
		this(dataPanel, container, table, new JScrollPane());
	}

	/**
	 * @see SpreadsheetNavigationPanelFactory#create(JPanel, SpreadsheetTable,
	 *      JScrollPane)
	 */
	@AssistedInject
	SpreadsheetNavigationPanel(UiDataPanel dataPanel,
			@Assisted JPanel container, @Assisted SpreadsheetTable table,
			@Assisted JScrollPane pane) {
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
		this.currentColumnVerifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				Object value = ((JFormattedTextField) input).getValue();
				return validateCurrentColumn((Integer) value);
			}
		};
		this.currentColumnListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateSelectedColumn((Integer) evt.getNewValue());
			}
		};
		this.currentRowVerifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				Object value = ((JFormattedTextField) input).getValue();
				return validateRow((Integer) value);
			}
		};
		this.currentRowListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateSelectedRow((Integer) evt.getNewValue());
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
		setupPane();
		setupTable();
		setupDataPanel();
	}

	protected void updateSelected() {
		int row = getSelectedRow();
		int column = getSelectedColumn();
		dataPanel.getCurrentRowField().setValue(row);
		dataPanel.getCurrentColumnField().setValue(column);
	}

	private void updateSelectedColumn(int column) {
		column = column - startColumnIndex;
		JTable table = getViewTable();
		if (column < 0 || column >= table.getColumnCount()) {
			return;
		}
		table.setColumnSelectionInterval(column, column);
		table.scrollRectToVisible(table.getCellRect(0, column, true));
	}

	private boolean validateCurrentColumn(int column) {
		if (column < startColumnIndex) {
			return false;
		}
		if (column >= getColumnCount()) {
			return false;
		}
		return true;
	}

	private void updateSelectedRow(int row) {
		row = row - getViewOffset() - startRowIndex;
		JTable table = getViewTable();
		if (row < 0 || row >= table.getRowCount()) {
			return;
		}
		table.setRowSelectionInterval(row, row);
		table.scrollRectToVisible(table.getCellRect(row, 0, true));
	}

	private boolean validateRow(int row) {
		if (row < startRowIndex) {
			return false;
		}
		if (row >= getViewRowCount()) {
			setViewMaximum(row + 1);
		}
		return true;
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
		dataPanel.setCurrentColumnFieldVerifier(currentColumnVerifier);
		dataPanel.getCurrentColumnField().addPropertyChangeListener(
				VALUE_PROPERTY, currentColumnListener);
		dataPanel.setCurrentRowFieldVerifier(currentRowVerifier);
		dataPanel.getCurrentRowField().addPropertyChangeListener(
				VALUE_PROPERTY, currentRowListener);
		setupViewTable();
		updateSelected();
		updateMaximum();
	}

	private void setupViewTable() {
		getViewModel().addTableModelListener(tableListener);
		getViewTable().getSelectionModel().addListSelectionListener(
				tableSelectionListener);
		getViewTable().getColumnModel().getSelectionModel()
				.addListSelectionListener(tableSelectionListener);
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
