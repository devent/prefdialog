package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import static com.google.inject.Guice.createInjector;

import java.awt.Rectangle;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Spreadsheet like table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SpreadsheetTable {

	private static SpreadsheetTableFactory factory;

	/**
	 * @see SpreadsheetTableFactory#create(JTable, SpreadsheetModel)
	 */
	public static SpreadsheetTable decorate(JTable table, SpreadsheetModel model) {
		return getFactory().create(table, model);
	}

	/**
	 * @see SpreadsheetTableFactory#create(JTable, SpreadsheetModel, ViewRange)
	 */
	public static SpreadsheetTable decorate(JTable table,
			SpreadsheetModel model, ViewRange range) {
		return getFactory().create(table, model, range);
	}

	private static SpreadsheetTableFactory getFactory() {
		if (factory == null) {
			Injector injector = createInjector(new SpreadsheetTableModule());
			factory = injector.getInstance(SpreadsheetTableFactory.class);
		}
		return factory;
	}

	private final JTable table;

	private final SpreadsheetTableModel model;

	private final ListSelectionListener selectionListener;

	private final AncestorListener ancestorListener;

	private final HierarchyBoundsListener boundsListener;

	private boolean ancestorAdded;

	private final TableBindings tableBindings;

	/**
	 * @see SpreadsheetTableFactory#create(JTable, SpreadsheetModel)
	 */
	@AssistedInject
	SpreadsheetTable(SpreadsheetTableModelFactory modelFactory,
			TableBindings tableBindings, @Assisted JTable table,
			@Assisted SpreadsheetModel model) {
		this(modelFactory, tableBindings, new JTable(), model, new ViewRange());
	}

	/**
	 * @see SpreadsheetTableFactory#create(JTable, SpreadsheetModel, ViewRange)
	 */
	@AssistedInject
	SpreadsheetTable(SpreadsheetTableModelFactory modelFactory,
			TableBindings tableBindings, @Assisted JTable table,
			@Assisted SpreadsheetModel model, @Assisted ViewRange range) {
		this.table = table;
		this.ancestorAdded = false;
		this.model = modelFactory.create(model, range);
		this.tableBindings = tableBindings;
		this.selectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				updateOffset();
			}
		};
		this.ancestorListener = new AncestorListener() {

			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
			}

			@Override
			public void ancestorAdded(AncestorEvent event) {
				ancestorAdded = true;
			}
		};
		this.boundsListener = new HierarchyBoundsListener() {

			@Override
			public void ancestorResized(HierarchyEvent e) {
				if (!ancestorAdded) {
					return;
				}
				Rectangle r = getTable().getVisibleRect();
				if (r.getSize().equals(getTable().getSize())) {
					setMaximum(getMaximum() + getExtendAmount());
				}
			}

			@Override
			public void ancestorMoved(HierarchyEvent e) {
			}
		};
		setupTable();
	}

	private void updateOffset() {
		int[] selected = table.getSelectedRows();
		int maxRow = table.getSelectionModel().getMaxSelectionIndex();
		int maximum = getMaximum();
		int offset = getOffset();
		if (maxRow == 0) {
			if (offset > 0) {
				setOffset(offset - 1);
				for (int row : selected) {
					table.addRowSelectionInterval(row + 1, row + 1);
				}
			}
		} else if (maxRow >= maximum - 1) {
			int difference = maximum - maxRow + offset;
			setOffset(difference);
			for (int row : selected) {
				table.addRowSelectionInterval(row - 1, row - 1);
			}
		}
	}

	private void setupTable() {
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(selectionListener);
		table.addAncestorListener(ancestorListener);
		table.addHierarchyBoundsListener(boundsListener);
		tableBindings.bindTable(table);
	}

	public JTable getTable() {
		return table;
	}

	public SpreadsheetTableModel getModel() {
		return model;
	}

	public void setViewRange(ViewRange range) {
		model.setViewRange(range);
	}

	public ViewRange getViewRange() {
		return model.getViewRange();
	}

	public void setOffset(int offset) {
		model.getViewRange().setOffset(offset);
	}

	public int getOffset() {
		return model.getOffset();
	}

	public void setMaximum(int maximum) {
		model.getViewRange().setMaximum(maximum);
	}

	public int getMaximum() {
		return model.getMaximum();
	}

	public void setExtendAmount(int amount) {
		model.getViewRange().setExtendAmount(amount);
	}

	public int getExtendAmount() {
		return model.getViewRange().getExtendAmount();
	}

}
