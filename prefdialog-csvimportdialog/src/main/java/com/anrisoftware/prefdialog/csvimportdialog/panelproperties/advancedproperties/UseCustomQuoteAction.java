/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.fields.FieldComponent;

public class UseCustomQuoteAction implements ActionListener {

	private FieldComponent<?> customQuoteField;

	private FieldComponent<?> quoteField;

	public void setCustomQuoteField(FieldComponent<?> field) {
		this.customQuoteField = field;
	}

	public void setQuoteField(FieldComponent<?> field) {
		this.quoteField = field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean enabled = customQuoteField.isEnabled();
		customQuoteField.setEnabled(!enabled);
		quoteField.setEnabled(enabled);
	}

}
