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
package com.anrisoftware.prefdialog.miscswing.components.menu;

import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Decorates a component to open the pop-up menu if the user clicks on it.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class PopupMenuComponent implements Serializable {

	/**
	 * Filters mouse events that are allowing to trigger the pop-up menu.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	public static interface MouseFilter extends Serializable {

		/**
		 * Test if the mouse event should trigger the pop-up menu.
		 * 
		 * @param e
		 *            the {@link MouseEvent}.
		 * 
		 * @return {@code true} to trigger the pop-up menu.
		 */
		boolean allow(MouseEvent e);

	}

	/**
	 * Returns the position for the pop-up menu.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	public static interface PopupMenuPosition extends Serializable {

		/**
		 * Returns the position for the pop-up menu relative to the specified
		 * component.
		 * 
		 * @param c
		 *            the {@link Component}.
		 * 
		 * @param mouseLocation
		 *            the {@link Point} location of the mouse.
		 * 
		 * @return the {@link Point} position of the pop-up menu.
		 */
		Point getPosition(Component c, Point mouseLocation);

	}

	/**
	 * The default mouse filter that allows all mouse events to trigger the
	 * pop-up menu.
	 */
	public static final MouseFilter ALLOW_ALL_MOUSE_FILTER = new MouseFilter() {

		@Override
		public boolean allow(MouseEvent e) {
			return true;
		}
	};

	/**
	 * The default popup menu position is direct under the component.
	 */
	public static final PopupMenuPosition DIRECT_UNDER_POSITION = new PopupMenuPosition() {

		@Override
		public Point getPosition(Component c, Point mouseLocation) {
			return new Point(-1, c.getHeight());
		}

	};

	/**
	 * @see #decorate(JComponent, JPopupMenu)
	 */
	public static PopupMenuComponent createPopup(JComponent component,
			JPopupMenu menu) {
		return decorate(component, menu);
	}

	/**
	 * Decorates the specified component to open the pop-up menu if the user
	 * clicks on it.
	 * 
	 * @param component
	 *            the {@link JComponent}.
	 * 
	 * @param menu
	 *            the {@link JPopupMenu}.
	 * 
	 * @return the {@link PopupMenuComponent}.
	 */
	public static PopupMenuComponent decorate(JComponent component,
			JPopupMenu menu) {
		return new PopupMenuComponent(component, menu);
	}

	private transient FocusListener menuFocusListener;

	private transient MouseListener componentMouseListener;

	private JComponent component;

	private JPopupMenu menu;

	private boolean alreadyShowingPopup;

	private boolean showPopup;

	private MouseFilter mouseFilter;

	private PopupMenuPosition popupMenuPosition;

	/**
	 * @see #decorate(JComponent, JPopupMenu)
	 */
	PopupMenuComponent(JComponent component, JPopupMenu menu) {
		this.showPopup = true;
		this.alreadyShowingPopup = false;
		this.component = component;
		this.menu = menu;
		this.mouseFilter = ALLOW_ALL_MOUSE_FILTER;
		this.popupMenuPosition = DIRECT_UNDER_POSITION;
		resolveObject();
		setComponent(component);
		setPopupMenu(menu);
	}

	private Object resolveObject() {
		if (menuFocusListener == null) {
			createMenuFocusListener();
		}
		if (componentMouseListener == null) {
			createComponentMouseListener();
		}
		return this;
	}

	private void createComponentMouseListener() {
		componentMouseListener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (!mouseFilter.allow(e)) {
					return;
				}
				if (alreadyShowingPopup) {
					showPopup = false;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (!mouseFilter.allow(e)) {
					return;
				}
				if (showPopup) {
					showPopup(e.getPoint());
				} else {
					showPopup = true;
				}
			}

		};
	}

	private void createMenuFocusListener() {
		menuFocusListener = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				alreadyShowingPopup = false;
			}

			@Override
			public void focusGained(FocusEvent e) {
				alreadyShowingPopup = true;
			}
		};
	}

	private void showPopup(Point mouseLocation) {
		Component c = component;
		Point position = popupMenuPosition.getPosition(c, mouseLocation);
		menu.show(c, position.x, position.y);
		menu.requestFocus();
	}

	/**
	 * Sets the component for which the pop-menu is shown.
	 * 
	 * @param component
	 *            the {@link JComponent}.
	 * 
	 * @throws NullPointerException
	 *             if the specified component is {@code null}.
	 */
	public void setComponent(JComponent component) {
		notNull(component);
		JComponent oldValue = this.component;
		this.component = component;
		if (oldValue != null) {
			oldValue.removeMouseListener(componentMouseListener);
		}
		component.addMouseListener(componentMouseListener);
	}

	/**
	 * Returns the component for which the pop-menu is shown.
	 * 
	 * @return the {@link JComponent}.
	 */
	public JComponent getComponent() {
		return component;
	}

	/**
	 * Sets the pop-up menu.
	 * 
	 * @param menu
	 *            the {@link JPopupMenu}.
	 * 
	 * @throws NullPointerException
	 *             if the specified menu is {@code null}.
	 */
	public void setPopupMenu(JPopupMenu menu) {
		notNull(component);
		JPopupMenu oldValue = this.menu;
		this.menu = menu;
		if (oldValue != null) {
			oldValue.removeFocusListener(menuFocusListener);
		}
		menu.addFocusListener(menuFocusListener);
	}

	/**
	 * Returns the pop-up menu.
	 * 
	 * @return the {@link JPopupMenu}.
	 */
	public JPopupMenu getMenu() {
		return menu;
	}

	/**
	 * Sets the mouse filter to decide if the pop-up menu should be triggered.
	 * 
	 * @param filter
	 *            the {@link MouseFilter}.
	 * 
	 * @throws NullPointerException
	 *             if the specified filter is {@code null}.
	 */
	public void setMouseFilter(MouseFilter filter) {
		notNull(mouseFilter);
		this.mouseFilter = filter;
	}

	/**
	 * Sets to calculate the position of the pop-up menu.
	 * 
	 * @param position
	 *            the {@link PopupMenuPosition}.
	 * 
	 * @throws NullPointerException
	 *             if the specified position is {@code null}.
	 */
	public void setPopupMenuPosition(PopupMenuPosition position) {
		notNull(position);
		this.popupMenuPosition = position;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("component", component)
				.append("menu", menu).toString();
	}
}
