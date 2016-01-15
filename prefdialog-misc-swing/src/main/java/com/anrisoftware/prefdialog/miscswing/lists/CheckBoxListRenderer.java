/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.lists;

import static javax.swing.BorderFactory.createEmptyBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * List renderer where the items are represented as check boxes and a selected
 * item is represented as a checked check box.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CheckBoxListRenderer<E> extends JCheckBox implements
		ListCellRenderer<E> {

	private static final Border cellNoFocusBorder = UIManager
			.getBorder("List.cellNoFocusBorder");

	private static final Border noFocusBorderDefault = createEmptyBorder(1, 1,
			1, 1);

	private static final Color dropCellBackground = UIManager
			.getColor("List.dropCellBackground");

	private static final Color dropCellForeground = UIManager
			.getColor("List.dropCellForeground");

	private static final Border focusSelectedCellHighlightBorder = UIManager
			.getBorder("List.focusSelectedCellHighlightBorder");

	private static final Border focusCellHighlightBorder = UIManager
			.getBorder("List.focusCellHighlightBorder");

	private final Border noFocusBorder;

	public CheckBoxListRenderer() {
		this.noFocusBorder = getNoFocusBorder();
		setOpaque(true);
		setBorder(noFocusBorder);
		setName("List.cellRenderer");
	}

	private Border getNoFocusBorder() {
		Border border = cellNoFocusBorder;
		return border == null ? noFocusBorderDefault : border;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list,
			E value, int index, boolean isSelected, boolean cellHasFocus) {
		setComponentOrientation(list.getComponentOrientation());
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		boolean isDropCell = isDropCell(list, index);
		if (isDropCell) {
			isSelected = true;
		}
		setupFont(list, isDropCell, isSelected);
		setBorder(getCellBorder(isSelected, cellHasFocus));
		setSelected(isSelected);
		setText(value.toString());
		return this;
	}

	private Border getCellBorder(boolean isSelected, boolean cellHasFocus) {
		if (cellHasFocus) {
			return getHighlighBorder(isSelected);
		} else {
			return noFocusBorder;
		}
	}

	private Border getHighlighBorder(boolean isSelected) {
		if (isSelected) {
			Border border = focusSelectedCellHighlightBorder;
			if (border == null) {
				border = focusCellHighlightBorder;
			}
			return border;
		} else {
			return focusCellHighlightBorder;
		}
	}

	private void setupFont(JList<? extends E> list, boolean isDropCell,
			boolean isSelected) {
		if (isDropCell) {
			setBackground(dropCellBackground);
			setForeground(dropCellForeground);
		} else {
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
		}
	}

	private boolean isDropCell(JList<? extends E> list, int index) {
		JList.DropLocation dropLocation = list.getDropLocation();
		return dropLocation != null && !dropLocation.isInsert()
				&& dropLocation.getIndex() == index;
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public boolean isOpaque() {
		Color back = getBackground();
		Component list = getParent();
		if (list != null) {
			list = list.getParent();
		}
		boolean colorMatch = (back != null) && (list != null)
				&& back.equals(list.getBackground()) && list.isOpaque();
		return !colorMatch && super.isOpaque();
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void validate() {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void invalidate() {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void repaint() {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void revalidate() {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void repaint(int x, int y, int width, int height) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void repaint(Rectangle r) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		if (propertyName == "text"
				|| ((propertyName == "font" || propertyName == "foreground")
						&& oldValue != newValue && getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey) != null)) {

			super.firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, byte oldValue,
			byte newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, char oldValue,
			char newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, double oldValue,
			double newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, float oldValue,
			float newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, int oldValue,
			int newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, long oldValue,
			long newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 */
	@Override
	public void firePropertyChange(String propertyName, short oldValue,
			short newValue) {
	}
}
