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
