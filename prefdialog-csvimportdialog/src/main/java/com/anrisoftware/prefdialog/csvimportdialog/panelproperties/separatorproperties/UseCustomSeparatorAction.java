/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.fields.FieldComponent;

public class UseCustomSeparatorAction implements ActionListener {

	private FieldComponent<?> customSeparatorCharField;

	private FieldComponent<?> separatorCharField;

	public void setCustomSeparatorCharField(FieldComponent<?> field) {
		this.customSeparatorCharField = field;
	}

	public void setSeparatorCharField(FieldComponent<?> field) {
		this.separatorCharField = field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean enabled = customSeparatorCharField.isEnabled();
		customSeparatorCharField.setEnabled(!enabled);
		separatorCharField.setEnabled(enabled);
	}

}
