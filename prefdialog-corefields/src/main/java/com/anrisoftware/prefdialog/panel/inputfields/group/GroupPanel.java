/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.group;

import static java.lang.String.format;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import static javax.swing.border.TitledBorder.LEADING;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.child.ChildComponent;
import com.google.inject.Inject;

/**
 * Sets a {@link UiGroupPanel} that will contain the fields that are in the
 * group.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class GroupPanel extends ChildComponent {

	private Object value;

	private final JPanel fieldsPanel;

	/**
	 * Set the {@link UiGroupPanel}.
	 */
	@Inject
	GroupPanel(JPanel panel) {
		super(panel);
		this.fieldsPanel = new JPanel();
		setLayout(createLayout());
		setup();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.FILL };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(5);
		layout.setVGap(5);
		return layout;
	}

	private void setup() {
		setupPanel();
		setupTitleText();
		setupFieldsPanel();
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.removeAll();
		panel.setBorder(createTitledBorder(null, "Group", LEADING,
				DEFAULT_POSITION));
		panel.add(fieldsPanel, "0, 0");
	}

	private void setupFieldsPanel() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.FILL };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(5);
		layout.setVGap(5);
		fieldsPanel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
		fieldsPanel.setLayout(layout);
	}

	private void setupTitleText() {
		TitledBorder border = (TitledBorder) getPanel().getBorder();
		Font font = defaultFontFor(border);
		border.setTitleFont(new Font(font.getFamily(), font.getStyle()
				| Font.BOLD, font.getSize()));
	}

	private Font defaultFontFor(TitledBorder border) {
		Font font = border.getTitleFont();
		font = font == null ? UIManager.getFont("Label.font") : font;
		font = font == null ? new JLabel().getFont() : font;
		return font;
	}

	@Override
	public void setTitle(String title) {
		TitledBorder border = (TitledBorder) getPanel().getBorder();
		border.setTitle(title);
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
	public void addField(FieldHandler<?> inputField) {
		int row = addRowToFieldsLayout();
		fieldsPanel.add(inputField.getAWTComponent(), format("0, %d", row));
	}

	private int addRowToFieldsLayout() {
		TableLayout layout = (TableLayout) fieldsPanel.getLayout();
		int rows = layout.getNumRow();
		int row = rows - 1;
		layout.insertRow(row, TableLayout.PREFERRED);
		layout.layoutContainer(getPanel());
		getPanel().repaint();
		return row;
	}

	@Override
	public boolean isInputValid() {
		return true;
	}
}
