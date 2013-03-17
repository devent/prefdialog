package com.anrisoftware.prefdialog.miscswing.menus;

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
 * Decorates a component to open the popup menu if the user clicks on it.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class PopupMenuComponent implements Serializable {

	/**
	 * @version 1.0
	 */
	private static final long serialVersionUID = 5022346586292642404L;

	/**
	 * Filters mouse events that are allowing to trigger the popup menu.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	public static interface MouseFilter extends Serializable {

		boolean allow(MouseEvent e);

	}

	/**
	 * Returns the position for the popup menu.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 1.0
	 */
	public static interface PopupMenuPosition extends Serializable {

		/**
		 * Returns the position for the popup menu relative to the specified
		 * component.
		 * 
		 * @param c
		 *            the {@link Component}.
		 * 
		 * @param mouseLocation
		 *            the {@link Point} location of the mouse.
		 * 
		 * @return the {@link Point} position of the popup menu.
		 */
		Point getPosition(Component c, Point mouseLocation);

	}

	/**
	 * The default mouse filter that allows all mouse events to trigger the
	 * popup menu.
	 */
	public static final MouseFilter ALLOW_ALL_MOUSE_FILTER = new MouseFilter() {

		/**
		 * @version 1.0
		 */
		private static final long serialVersionUID = -748005930004905276L;

		@Override
		public boolean allow(MouseEvent e) {
			return true;
		}
	};

	/**
	 * The default popup menu position is direct under the component.
	 */
	public static final PopupMenuPosition DIRECT_UNDER_POSITION = new PopupMenuPosition() {

		/**
		 * @version 1.0
		 */
		private static final long serialVersionUID = 1209985974108811540L;

		@Override
		public Point getPosition(Component c, Point mouseLocation) {
			return new Point(-1, c.getHeight());
		}

	};

	private transient FocusListener menuFocusListener;

	private transient MouseListener componentMouseListener;

	private JComponent component;

	private JPopupMenu menu;

	private boolean alreadyShowingPopup;

	private boolean showPopup;

	private MouseFilter mouseFilter;

	private PopupMenuPosition popupMenuPosition;

	/**
	 * Decorates the specified component to open the popup menu if the user
	 * clicks on it.
	 * 
	 * @param component
	 *            the {@link JComponent}.
	 * 
	 * @param menu
	 *            the {@link JPopupMenu}.
	 */
	public PopupMenuComponent(JComponent component, JPopupMenu menu) {
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

	public void setComponent(JComponent component) {
		JComponent oldValue = this.component;
		this.component = component;
		if (oldValue != null) {
			oldValue.removeMouseListener(componentMouseListener);
		}
		component.addMouseListener(componentMouseListener);
	}

	public JComponent getComponent() {
		return component;
	}

	public void setPopupMenu(JPopupMenu menu) {
		JPopupMenu oldValue = this.menu;
		this.menu = menu;
		if (oldValue != null) {
			oldValue.removeFocusListener(menuFocusListener);
		}
		menu.addFocusListener(menuFocusListener);
	}

	public JPopupMenu getMenu() {
		return menu;
	}

	public void setMouseFilter(MouseFilter filter) {
		this.mouseFilter = filter;
	}

	public void setPopupMenuPosition(PopupMenuPosition position) {
		this.popupMenuPosition = position;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("component", component)
				.append("menu", menu).toString();
	}
}
