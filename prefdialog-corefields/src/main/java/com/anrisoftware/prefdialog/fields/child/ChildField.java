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

import static com.anrisoftware.prefdialog.fields.child.ChildPluginModule.CHILDREN_SCROLL_NAME;
import static com.anrisoftware.prefdialog.fields.child.ChildPluginModule.TITLE_SEPARATOR_NAME;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.SwingConstants.HORIZONTAL;
import info.clearthought.layout.TableLayout;

import java.awt.Container;
import java.awt.Font;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.google.inject.assistedinject.Assisted;

/**
 * Child panel field. The child panel is a panel that contains other fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class ChildField extends AbstractTitleField<JPanel, Container> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = Child.class;

	private final ChildFieldLogger log;

	private final TableLayout layout;

	private final JSeparator separator;

	private final JScrollPane scrollPane;

	private TableLayout childrenPanelLayout;

	@Inject
	ChildField(ChildFieldLogger logger, @Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(new JPanel(), container, parentObject, field);
		this.log = logger;
		this.layout = createLayout();
		this.separator = new JSeparator(HORIZONTAL);
		this.scrollPane = new JScrollPane();
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
		setupScrollPane();
		setupChildLabel();
		setupChildrenPanel();
	}

	private void setupPanel() {
		Container panel = getContainer();
		panel.removeAll();
		panel.setLayout(layout);
		panel.add(getTitleLabel(), "0, 0");
		panel.add(separator, "0, 1");
		panel.add(scrollPane, "0, 2");
	}

	private void setupScrollPane() {
		scrollPane.setViewportView(getComponent());
		scrollPane.setBorder(createEmptyBorder(0, 0, 0, 0));
	}

	private void setupChildLabel() {
		Font font = getTitleLabel().getFont();
		getTitleLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	private void setupChildrenPanel() {
		double[] col = { FILL };
		double[] row = {};
		childrenPanelLayout = new TableLayout(col, row);
		childrenPanelLayout.setHGap(5);
		childrenPanelLayout.setVGap(5);
		getComponent().setLayout(childrenPanelLayout);
		getComponent().setBorder(createEmptyBorder(0, 6, 0, 6));
	}

	@Override
	public void setName(String name) {
		scrollPane.setName(format("%s-%s", name, CHILDREN_SCROLL_NAME));
		separator.setName(format("%s-%s", name, TITLE_SEPARATOR_NAME));
		super.setName(name);
	}

	@Override
	public void setShowTitle(boolean show) {
		if (!show) {
			removeTitleSeparator();
		} else {
			insertTitleSeparator();
		}
		super.setShowTitle(show);
	}

	private void insertTitleSeparator() {
		Container panel = getContainer();
		panel.remove(separator);
		layout.deleteRow(1);
	}

	private void removeTitleSeparator() {
		Container panel = getContainer();
		layout.insertRow(1, PREFERRED);
		panel.remove(separator);
	}

	@Override
	public void addField(FieldComponent<?> field) {
		super.addField(field);
		addToChildrenPanel(field);
	}

	private void addToChildrenPanel(FieldComponent<?> field) {
		int rows = childrenPanelLayout.getNumRow();
		childrenPanelLayout.insertRow(rows, TableLayout.PREFERRED);
		// layout.layoutContainer(getPanel());
		// getPanel().repaint();
		getComponent().add(field.getAWTComponent(), format("0, %d", rows));
	}
}
