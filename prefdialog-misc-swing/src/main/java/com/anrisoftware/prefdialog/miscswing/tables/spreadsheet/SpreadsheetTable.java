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

	private EditOnSelection editOnSelection;

	private SpreadsheetCellRenderer renderer;

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
		this.editOnSelection = new EditOnSelection(table);
		this.renderer = new SpreadsheetCellRenderer();
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
		table.setDefaultRenderer(Object.class, renderer);
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(selectionListener);
		table.addAncestorListener(ancestorListener);
		table.addHierarchyBoundsListener(boundsListener);
		tableBindings.bindTable(table);
		table.getColumnModel().getSelectionModel()
				.addListSelectionListener(editOnSelection);
		table.getSelectionModel().addListSelectionListener(editOnSelection);
	}

	/**
	 * Returns the decorated table.
	 * 
	 * @return the {@link JTable}.
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Returns the spreadsheet table model.
	 * 
	 * @return the {@link SpreadsheetTableModel}.
	 */
	public SpreadsheetTableModel getModel() {
		return model;
	}

	/**
	 * Sets the view range for the model.
	 * 
	 * @param range
	 *            the {@link ViewRange}.
	 */
	public void setViewRange(ViewRange range) {
		model.setViewRange(range);
	}

	/**
	 * Returns the view range for the model.
	 * 
	 * @return the {@link ViewRange}.
	 */
	public ViewRange getViewRange() {
		return model.getViewRange();
	}

	/**
	 * Sets the offset of the view.
	 * 
	 * @param offset
	 *            the offset index.
	 */
	public void setOffset(int offset) {
		model.getViewRange().setOffset(offset);
	}

	/**
	 * Returns the offset of the view.
	 * 
	 * @return the offset index.
	 */
	public int getOffset() {
		return model.getOffset();
	}

	/**
	 * Sets the maximum of the view.
	 * 
	 * @param maximum
	 *            the maximum rows.
	 */
	public void setMaximum(int maximum) {
		model.getViewRange().setMaximum(maximum);
	}

	/**
	 * Returns the maximum of the view.
	 * 
	 * @return the maximum.
	 */
	public int getMaximum() {
		return model.getMaximum();
	}

	/**
	 * Sets how much the view should be extended if the user selects over the
	 * maximum of the view.
	 * 
	 * @param amount
	 *            the amount to extend.
	 */
	public void setExtendAmount(int amount) {
		model.getViewRange().setExtendAmount(amount);
	}

	/**
	 * Returns how much the view should be extended if the user selects over the
	 * maximum of the view.
	 * 
	 * @return the amount to extend.
	 */
	public int getExtendAmount() {
		return model.getViewRange().getExtendAmount();
	}

}
