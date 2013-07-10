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
package com.anrisoftware.prefdialog.miscswing.lists;

import static com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent.createPopup;
import static javax.swing.SwingUtilities.isRightMouseButton;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent;
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent.MouseFilter;
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent.PopupMenuPosition;

/**
 * Let the user click with the right mouse key on an list item and show a pop-up
 * menu.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class PopupMenuList<E> {

	/**
	 * @see #decorate(JList, JPopupMenu)
	 */
	public static <E> PopupMenuList<E> createPopupList(JList<E> list,
			JPopupMenu menu) {
		return decorate(list, menu);
	}

	/**
	 * Decorates the specified list to open the pop-up menu if the user clicks
	 * on an list item.
	 * 
	 * @param list
	 *            the {@link JList}.
	 * 
	 * @param menu
	 *            the {@link JPopupMenu}.
	 * 
	 * @return the {@link PopupMenuComponent}.
	 */
	public static <E> PopupMenuList<E> decorate(JList<E> list, JPopupMenu menu) {
		return new PopupMenuList<E>(list, menu);
	}

	private final JList<E> list;

	private final PopupMenuComponent popup;

	protected int selectedIndex;

	/**
	 * @see #decorate(JList, JPopupMenu)
	 */
	PopupMenuList(JList<E> list, JPopupMenu menu) {
		this.list = list;
		this.popup = createPopup(list, menu);
		setupPopup();
		setupList();
	}

	private void setupList() {
		list.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						selectedIndex = list.getSelectedIndex();
					}
				});
	}

	@SuppressWarnings("serial")
	private void setupPopup() {
		popup.setMouseFilter(new MouseFilter() {

			@Override
			public boolean allow(MouseEvent e) {
				if (selectedIndex == -1) {
					return false;
				}
				int mouseIndex = list.locationToIndex(e.getPoint());
				return selectedIndex == mouseIndex && isRightMouseButton(e);
			}
		});
		popup.setPopupMenuPosition(new PopupMenuPosition() {

			@Override
			public Point getPosition(Component c, Point mouseLocation) {
				return new Point(mouseLocation.x, mouseLocation.y);
			}
		});
	}

	/**
	 * Returns the swing list.
	 * 
	 * @return the {@link JList}.
	 */
	public JList<E> getList() {
		return list;
	}

	/**
	 * Returns the pop-up menu.
	 * 
	 * @return the {@link JPopupMenu}.
	 */
	public JPopupMenu getMenu() {
		return popup.getMenu();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(popup).toString();
	}
}
