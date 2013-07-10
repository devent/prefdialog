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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class CharsetEditor implements ComboBoxEditor {

	private final ComboBoxEditor editor;

	CharsetEditor() {
		this.editor = new JComboBox<Object>().getEditor();
	}

	@Override
	public Component getEditorComponent() {
		return editor.getEditorComponent();
	}

	@Override
	public void setItem(Object anObject) {
		editor.setItem(anObject);
	}

	@Override
	public Object getItem() {
		Object item = editor.getItem();
		if (item != null) {
			try {
				return asCharset(item);
			} catch (UnsupportedCharsetException e) {
				return item;
			}
		} else {
			return null;
		}
	}

	private Charset asCharset(Object item) {
		return Charset.forName(item.toString());
	}

	@Override
	public void selectAll() {
		editor.selectAll();
	}

	@Override
	public void addActionListener(ActionListener l) {
		editor.addActionListener(l);
	}

	@Override
	public void removeActionListener(ActionListener l) {
		editor.removeActionListener(l);
	}

}
