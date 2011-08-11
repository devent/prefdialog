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
package com.globalscalingsoftware.prefdialog.panel.inputfield.combobox;

import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;

import com.globalscalingsoftware.prefdialog.panel.inputfield.shared.AbstractLabelFieldPanel;

public class ComboBoxPanel extends AbstractLabelFieldPanel<JComboBox> {

	public ComboBoxPanel() {
		super(new JComboBox());
		setup();
	}

	private void setup() {
	}

	@Override
	public Object getValue() {
		return getPanelField().getSelectedItem();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setSelectedItem(value);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	public void setValues(Collection<?> values) {
		MutableComboBoxModel model = getMutableModel();
		for (Object object : values) {
			model.addElement(object);
		}
	}

	private MutableComboBoxModel getMutableModel() {
		return (MutableComboBoxModel) getPanelField().getModel();
	}

	public void setRenderer(ListCellRenderer renderer) {
		getPanelField().setRenderer(renderer);
	}

	public void setModel(ComboBoxModel model) {
		getPanelField().setModel(model);
	}

}
