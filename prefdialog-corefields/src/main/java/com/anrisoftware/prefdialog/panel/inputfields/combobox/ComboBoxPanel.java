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
package com.anrisoftware.prefdialog.panel.inputfields.combobox;

import java.util.Collection;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * Sets a {@link JComboBox} as the field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ComboBoxPanel extends AbstractLabelFieldPanel<JComboBox> {

	private final ComboBoxPanelLogger log;

	/**
	 * Create and setup the {@link JComboBox}.
	 */
	@Inject
	ComboBoxPanel(ComboBoxPanelLogger logger) {
		super(new JComboBox());
		this.log = logger;
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

	/**
	 * Sets the new {@link Collection} of values for the combobox.
	 */
	public void setValues(Collection<?> values) {
		MutableComboBoxModel model = getMutableModel();
		for (Object object : values) {
			model.addElement(object);
		}
		log.valuesSet(this, values);
	}

	private MutableComboBoxModel getMutableModel() {
		return (MutableComboBoxModel) getPanelField().getModel();
	}

	/**
	 * Sets a new {@link ListCellRenderer} for the combobox.
	 */
	public void setRenderer(ListCellRenderer renderer) {
		getPanelField().setRenderer(renderer);
		log.rendererSet(this, renderer);
	}

	/**
	 * Sets a new {@link ComboBoxModel} for the combobox.
	 */
	public void setModel(ComboBoxModel model) {
		getPanelField().setModel(model);
		log.modelSet(this, model);
	}

}
