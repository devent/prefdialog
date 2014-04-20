/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.buttongroup;

import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.BUTTONS_ROW_NAME;
import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.BUTTON_NAME;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;
import info.clearthought.layout.TableLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Panel that aligns the buttons in a row.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ButtonsRowPanel extends JPanel {

	private TableLayout layout;

	private String name;

	private final ListDataListener actionsListener;

	private final List<JButton> buttons;

	private ListModel<Action> model;

	/**
	 * Create the {@link JPanel} and sets a one-row {@link TableLayout}.
	 */
	ButtonsRowPanel() {
		this.name = "";
		this.buttons = new ArrayList<JButton>();
		this.actionsListener = new ListDataListener() {

			@Override
			public void intervalRemoved(ListDataEvent e) {
				removeInterval(e.getIndex0(), e.getIndex1());
			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				addInterval(e.getIndex0(), e.getIndex1());
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				replaceInterval(e.getIndex0(), e.getIndex1());
			}
		};
		setup();
	}

	private void setup() {
		double[] col = {};
		double[] row = { TableLayout.PREFERRED };
		layout = new TableLayout(col, row);
		layout.setHGap(3);
		setLayout(layout);
	}

	private void removeInterval(int index0, int index1) {
		for (int i = index0; i < index1; i++) {
			JButton button = buttons.get(i);
			remove(button);
			buttons.remove(i);
			layout.deleteColumn(i);
		}
		updateButtonNames();
		updateLayout();
	}

	private void addInterval(int index0, int index1) {
		for (int i = index0; i < index1; i++) {
			layout.insertColumn(i, PREFERRED);
			JButton button = new JButton(model.getElementAt(i));
			add(button, format("%d, 0", i));
			buttons.add(button);
		}
		updateButtonNames();
		updateLayout();
	}

	private void replaceInterval(int index0, int index1) {
		for (int i = index0; i < index1; i++) {
			JButton button = buttons.get(i);
			remove(button);
			button = new JButton(model.getElementAt(i));
			add(button, format("%d, 0", i));
			buttons.set(i, button);
		}
		updateButtonNames();
		updateLayout();
	}

	private void updateLayout() {
		layout.layoutContainer(this);
		repaint();
	}

	/**
	 * Sets the model that contains the button actions.
	 * 
	 * @param newModel
	 *            the {@link ListModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	public void setModel(ListModel<Action> newModel) {
		notNull(newModel, "The model cannot be null.");
		if (model == newModel) {
			return;
		}
		replaceModel(newModel);
	}

	private void replaceModel(ListModel<Action> newModel) {
		if (model != null) {
			model.removeListDataListener(actionsListener);
			int oldSize = model.getSize();
			removeInterval(0, oldSize);
		}
		model = newModel;
		addInterval(0, model.getSize());
	}

	/**
	 * Sets the name of this buttons panel and the name of all buttons.
	 */
	@Override
	public void setName(String name) {
		super.setName(format("%s-%s", name, BUTTONS_ROW_NAME));
		this.name = name;
		updateButtonNames();
	}

	private void updateButtonNames() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setName(format("%s-%d-%s", name, i, BUTTON_NAME));
		}
	}

	/**
	 * Returns the button in this buttons row panel with the specified index.
	 * 
	 * @param index
	 *            the index.
	 * 
	 * @return the {@link JButton} with the index.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range.
	 */
	public JButton getButton(int index) {
		return buttons.get(index);
	}

}
