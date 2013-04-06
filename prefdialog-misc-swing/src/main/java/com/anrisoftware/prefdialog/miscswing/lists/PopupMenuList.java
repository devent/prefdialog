package com.anrisoftware.prefdialog.miscswing.lists;

import static javax.swing.SwingUtilities.isRightMouseButton;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent;
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent.MouseFilter;
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent.PopupMenuPosition;

/**
 * Let the user click with the right mouse key on an list item and show a popup
 * menu.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class PopupMenuList<E> {

	private final JList<E> list;

	private final PopupMenuComponent popup;

	protected int selectedIndex;

	public PopupMenuList(JList<E> list, JPopupMenu menu) {
		this.list = list;
		this.popup = new PopupMenuComponent(list, menu);
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

	private void setupPopup() {
		popup.setMouseFilter(new MouseFilter() {

			/**
			 *
			 */
			private static final long serialVersionUID = 6715512934267416570L;

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

			/**
			 *
			 */
			private static final long serialVersionUID = -2459125573414393762L;

			@Override
			public Point getPosition(Component c, Point mouseLocation) {
				return new Point(mouseLocation.x, mouseLocation.y);
			}
		});
	}

	public JList<E> getList() {
		return list;
	}
}
