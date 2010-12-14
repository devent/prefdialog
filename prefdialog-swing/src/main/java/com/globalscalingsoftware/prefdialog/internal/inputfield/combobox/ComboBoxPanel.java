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
package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

@SuppressWarnings("serial")
public class ComboBoxPanel extends AbstractLabelFieldPanel<JComboBox> {

	private static class ValuesComboBoxModel extends DefaultComboBoxModel {

		public ValuesComboBoxModel(Collection<?> values) {
			super(values.toArray());
		}

	}

	public ComboBoxPanel() {
		super(new JComboBox());
	}

	public void setValues(Object values) {
		ValuesComboBoxModel model = new ValuesComboBoxModel(
				(Collection<?>) values);
		getPanelField().setModel(model);
	}

	public void setModel(ComboBoxModel model) {
		getPanelField().setModel(model);
	}

	@Override
	public Object getValue() {
		return getPanelField().getSelectedItem();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setSelectedItem(value);
	}

}
