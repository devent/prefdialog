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
package com.anrisoftware.prefdialog.fields.child;

import static com.anrisoftware.prefdialog.fields.child.ChildService.TITLE_SEPARATOR_NAME;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.SwingConstants.HORIZONTAL;
import info.clearthought.layout.TableLayout;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Child panel field. The child panel is a panel that contains other fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ChildField extends AbstractTitleField<JPanel> {

	private final ChildFieldLogger log;

	private final TableLayout layout;

	private final JSeparator separator;

	private TableLayout childrenPanelLayout;

	private boolean titleSeparatorShow;

	/**
	 * @see ChildFieldFactory#create(Object, String)
	 */
	@AssistedInject
	ChildField(ChildFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new JPanel(), parentObject, fieldName);
		this.log = logger;
		this.layout = createLayout();
		this.separator = new JSeparator(HORIZONTAL);
		this.titleSeparatorShow = true;
		setup();
	}

	private TableLayout createLayout() {
		double[] col = { FILL };
		double[] row = { PREFERRED, PREFERRED, FILL };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(5);
		layout.setVGap(5);
		return layout;
	}

	private void setup() {
		setupPanel();
		setupChildLabel();
		setupChildrenPanel();
	}

	private void setupPanel() {
		JPanel panel = getContainer();
		panel.removeAll();
		panel.setLayout(layout);
		panel.add(getTitleLabel(), "0, 0");
		panel.add(separator, "0, 1");
		panel.add(getComponent(), "0, 2");
	}

	private void setupChildLabel() {
		JLabel label = getTitleLabel();
		Font font = label.getFont();
		label.setFont(new Font(font.getFamily(), font.getStyle() | Font.BOLD,
				font.getSize()));
	}

	private void setupChildrenPanel() {
		JPanel panel = getComponent();
		double[] col = { FILL };
		double[] row = {};
		childrenPanelLayout = new TableLayout(col, row);
		childrenPanelLayout.setHGap(5);
		childrenPanelLayout.setVGap(5);
		panel.setLayout(childrenPanelLayout);
		panel.setBorder(createEmptyBorder(0, 6, 0, 6));
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		separator.setName(format("%s-%s", name, TITLE_SEPARATOR_NAME));
	}

	@Override
	public void setShowTitle(boolean show) {
		super.setShowTitle(show);
		if (titleSeparatorShow == show) {
			return;
		}
		if (show) {
			insertTitleSeparator();
		} else {
			removeTitleSeparator();
		}
	}

	private void insertTitleSeparator() {
		Container panel = getContainer();
		layout.insertRow(1, PREFERRED);
		panel.add(separator, "0, 1");
		getComponent().revalidate();
		titleSeparatorShow = true;
	}

	private void removeTitleSeparator() {
		Container panel = getContainer();
		panel.remove(separator);
		layout.deleteRow(1);
		getComponent().revalidate();
		titleSeparatorShow = false;
	}

	@Override
	public void addField(FieldComponent<?> field) {
		super.addField(field);
		addToChildrenPanel(field);
	}

	private void addToChildrenPanel(FieldComponent<?> field) {
		JPanel panel = getComponent();
		int rows = childrenPanelLayout.getNumRow();
		double height = field.getHeight().doubleValue();
		childrenPanelLayout.insertRow(rows, height);
		layout.layoutContainer(panel);
		panel.add(field.getAWTComponent(), format("0, %d", rows));
		getComponent().revalidate();
		log.addChildField(this, field);
	}
}
