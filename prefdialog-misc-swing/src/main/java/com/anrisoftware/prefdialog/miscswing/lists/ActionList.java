package com.anrisoftware.prefdialog.miscswing.lists;

import static java.awt.event.ActionEvent.ACTION_PERFORMED;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.event.EventListenerSupport;

/**
 * Decorates a swing list so that the added action listeners are informed if the
 * user select an item. The action listeners can be informed multiple time with
 * the same list item, so caution must be taken.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ActionList<E> {

	/**
	 * @see #decorate(JList)
	 */
	public static <E> ActionList<E> createActionList(JList<E> list) {
		return decorate(list);
	}

	/**
	 * Create the action list from the specified list.
	 * 
	 * @param list
	 *            the {@link JList}.
	 * 
	 * @return the new {@link ActionList}.
	 */
	public static <E> ActionList<E> decorate(JList<E> list) {
		return new ActionList<E>(list);
	}

	private final EventListenerSupport<ActionListener> actionListeners;

	private final JList<E> list;

	private String command;

	/**
	 * @see #decorate(JList)
	 */
	ActionList(JList<E> list) {
		this.actionListeners = new EventListenerSupport<ActionListener>(
				ActionListener.class);
		this.list = list;
		setupList();
	}

	private void setupList() {
		list.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							fireAction();
						}
					}
				});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fireAction();
			}
		});
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
					fireAction();
				}
				if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					fireAction();
				}
			}
		});
	}

	private void fireAction() {
		actionListeners.fire().actionPerformed(
				new ActionEvent(list, ACTION_PERFORMED, command));
	}

	/**
	 * Sets the action command string that is returned in the action event.
	 * 
	 * @param command
	 *            the action command string.
	 * 
	 * @see ActionEvent#getActionCommand()
	 */
	public void setActionCommand(String command) {
		this.command = command;
	}

	/**
	 * Returns the action command string that is returned in the action event.
	 * 
	 * @return the action command string.
	 * 
	 * @see ActionEvent#getActionCommand()
	 */
	public String getActionCommand() {
		return command;
	}

	/**
	 * Adds the action listener that is informed if the user select an item.
	 * 
	 * @param l
	 *            the {@link ActionListener}.
	 */
	public void addActionListener(ActionListener l) {
		actionListeners.addListener(l);
	}

	/**
	 * Removes an old action listener.
	 * 
	 * @param l
	 *            the {@link ActionListener}.
	 */
	public void removeActionListener(ActionListener l) {
		actionListeners.removeListener(l);
	}
}
