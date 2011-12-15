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
package com.anrisoftware.prefdialog.panel.inputfields.checkbox;

import javax.swing.JCheckBox;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * Sets the {@link JCheckBox} as the field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class CheckBoxPanel extends AbstractLabelFieldPanel<JCheckBox> {

	/**
	 * Create and setup the {@link JCheckBox}.
	 */
	CheckBoxPanel() {
		super(new JCheckBox());
		setup();
	}

	private void setup() {
	}

	/**
	 * Set the text of the {@link JCheckBox}.
	 */
	public void setText(String text) {
		getPanelField().setText(text);
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setSelected((Boolean) value);
	}

	@Override
	public Object getValue() {
		return getPanelField().isSelected();
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

}
