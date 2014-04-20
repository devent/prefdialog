/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties;

import java.nio.charset.Charset;

import javax.swing.DefaultComboBoxModel;

import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ItemDefault;

@SuppressWarnings("serial")
public class CharsetModel extends DefaultComboBoxModel<Object> {

	@Override
	public void insertElementAt(Object item, int index) {
		if (item instanceof ItemDefault) {
			super.insertElementAt(item, index);
		} else if (item instanceof Charset) {
			super.insertElementAt(item, index);
		}
	}
}
