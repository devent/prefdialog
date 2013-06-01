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
 * user select an item.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ActionList<E> {

	private final EventListenerSupport<ActionListener> actionListeners;

	private final JList<E> list;

	private String command;

	public ActionList(JList<E> list) {
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
						if (e.getValueIsAdjusting()) {
							return;
						}
						if (list.getSelectedValue() != null) {
							fireAction();
						}
					}
				});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedValue() != null) {
					fireAction();
				}
			}
		});
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
					if (list.getSelectedValue() != null) {
						fireAction();
					}
				}
				if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					if (list.getSelectedValue() != null) {
						fireAction();
					}
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
