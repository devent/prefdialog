package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

/**
 * Button that shows a popup menu if clicked.
 * 
 * <pre>
 * popupButton = new JButton();
 * menu = new JPopupMenu();
 * PopupButton.decorate(popupButton, menu);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class PopupButton implements Serializable {

	/**
	 * Decorates a button to open the specified menu if the user clicks on the
	 * button.
	 * 
	 * @param button
	 *            the {@link JButton}.
	 * 
	 * @param menu
	 *            the {@link JPopupMenu}.
	 * 
	 * @return the decorated {@link JButton}.
	 */
	public static PopupButton decorate(JButton button, JPopupMenu menu) {
		return new PopupButton(button, menu);
	}

	private final FocusListener menuFocusListener;

	private final ActionListener buttonActionListener;

	private final MouseAdapter buttonMouseListener;

	private JButton button;

	private JPopupMenu menu;

	private boolean alreadyShowingPopup;

	private boolean showPopup;

	PopupButton(JButton button, JPopupMenu menu) {
		this.alreadyShowingPopup = false;
		this.showPopup = true;
		this.button = button;
		this.menu = menu;
		this.menuFocusListener = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				alreadyShowingPopup = false;
			}

			@Override
			public void focusGained(FocusEvent e) {
				alreadyShowingPopup = true;
			}
		};
		buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPopup) {
					Component c = (Component) e.getSource();
					PopupButton.this.menu.show(c, -1, c.getHeight());
					PopupButton.this.menu.requestFocus();
				} else {
					showPopup = true;
				}
			}
		};
		buttonMouseListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (alreadyShowingPopup) {
					showPopup = false;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup = true;
			}
		};
		setButton(button);
		setPopupMenu(menu);
	}

	public void setButton(JButton button) {
		JButton oldValue = this.button;
		this.button = button;
		if (oldValue != null) {
			oldValue.removeActionListener(buttonActionListener);
			oldValue.removeMouseListener(buttonMouseListener);
		}
		button.addActionListener(buttonActionListener);
		button.addMouseListener(buttonMouseListener);
	}

	public void setPopupMenu(JPopupMenu menu) {
		JPopupMenu oldValue = this.menu;
		this.menu = menu;
		if (oldValue != null) {
			oldValue.removeFocusListener(menuFocusListener);
		}
		menu.addFocusListener(menuFocusListener);
	}
}
