package com.anrisoftware.prefdialog.miscswing.lists;

import java.awt.event.MouseAdapter;

import javax.swing.JList;

/**
 * Let the user click with the right moise key on an list item and show a popup
 * menu.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class PopupMenuList<E> {

	private final JList<E> list;

	public PopupMenuList(JList<E> list) {
		this.list = list;
		setupMouse();
	}

	private void setupMouse() {
		list.addMouseListener(new MouseAdapter() {
		});
	}

	public JList<E> getList() {
		return list;
	}
}
