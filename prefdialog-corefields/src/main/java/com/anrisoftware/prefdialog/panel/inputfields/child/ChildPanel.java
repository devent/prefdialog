/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import com.anrisoftware.prefdialog.FieldHandler;
import com.google.inject.Inject;

/**
 * Setup a panel that have a title and separator to divide the title from the
 * child fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ChildPanel extends ChildComponent {

	/**
	 * Name prefix for the title separator.
	 */
	public static final String TITLE_SEPARATOR = "titleseparator";

	/**
	 * Name prefix for the child fields panel.
	 */
	public static final String CHILD_FIELDS = "childfields";

	/**
	 * Name prefix for the child scroll pane.
	 */
	public static final String CHILD_SCROLL = "childscroll";

	private Object value;

	private final JSeparator separator;

	private final JScrollPane scrollPane;

	private final TableLayout layout;

	/**
	 * Setups the panel.
	 */
	@Inject
	ChildPanel(JPanel panel) {
		super(panel);
		this.separator = new JSeparator(JSeparator.HORIZONTAL);
		this.scrollPane = new JScrollPane();
		this.layout = createLayout();
		setLayout(layout);
		setup();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED,
				TableLayout.FILL };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(5);
		layout.setVGap(5);
		return layout;
	}

	private void setup() {
		setupPanel();
		setupScrollPane();
		setupChildLabel();
		setupChildPanel();
	}

	private void setupChildPanel() {
		double[] col = { TableLayout.FILL };
		double[] row = {};
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(5);
		layout.setVGap(5);
		getPanelField().setLayout(layout);
		// getPanelField().setBorder(BorderFactory.createEmptyBorder(0, 6, 0,
		// 6));
		getPanelField().setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void setupScrollPane() {
		scrollPane.setViewportView(getPanelField());
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.removeAll();
		panel.add(getLabel(), "0, 0");
		panel.add(separator, "0, 1");
		panel.add(scrollPane, "0, 2");
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 0, 6));
	}

	private void setupChildLabel() {
		Font font = getLabel().getFont();
		getLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	@Override
	public void setName(String name) {
		scrollPane.setName(format("%s-%s", CHILD_SCROLL, name));
		getPanelField().setName(format("%s-%s", CHILD_FIELDS, name));
		separator.setName(format("%s-%s", TITLE_SEPARATOR, name));
		super.setName(name);
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setShowTitle(boolean show) {
		if (!show) {
			JPanel panel = (JPanel) getAWTComponent();
			panel.remove(separator);
			layout.deleteRow(0);
		}
		super.setShowTitle(show);
	}

	@Override
	public void addField(FieldHandler<?> inputField) {
		int row = addRowToFieldsLayout();
		getPanelField().add(inputField.getAWTComponent(), format("0, %d", row));
	}

	private int addRowToFieldsLayout() {
		TableLayout layout = (TableLayout) getPanelField().getLayout();
		int rows = layout.getNumRow();
		layout.insertRow(rows, TableLayout.PREFERRED);
		// layout.layoutContainer(getPanel());
		// getPanel().repaint();
		return rows;
	}

	@Override
	public boolean isInputValid() {
		return true;
	}
}
