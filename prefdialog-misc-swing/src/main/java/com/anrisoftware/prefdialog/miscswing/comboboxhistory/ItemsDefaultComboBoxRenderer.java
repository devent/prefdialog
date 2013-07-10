/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static java.awt.Font.ITALIC;

import java.awt.Component;
import java.awt.Font;
import java.io.Serializable;

import javax.inject.Inject;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import com.google.inject.assistedinject.Assisted;

/**
 * Renders default items of the combo box in special way.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
class ItemsDefaultComboBoxRenderer implements ListCellRenderer, Serializable {

	private final ListCellRenderer renderer;

	private final Font itemDefaultFont;

	/**
	 * @see ItemsDefaultComboBoxRendererFactory#create(ListCellRenderer)
	 */
	@Inject
	ItemsDefaultComboBoxRenderer(@Assisted ListCellRenderer renderer) {
		this.renderer = renderer;
		this.itemDefaultFont = createItemDefaultFont(UIManager
				.getFont("Label.font"));
	}

	private Font createItemDefaultFont(Font font) {
		return new Font(font.getName(), font.getStyle() | ITALIC,
				font.getSize());
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Component component = renderer.getListCellRendererComponent(list,
				value, index, isSelected, cellHasFocus);
		if (value instanceof ItemDefault) {
			component.setFont(itemDefaultFont);
		}
		return component;
	}
}
