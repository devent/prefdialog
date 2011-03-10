package com.globalscalingsoftware.prefdialog.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

/**
 * A {@link Action} that will delegate all methods to a parent {@link Action
 * action} and will call a {@link Runnable callback} if the method
 * {@link #actionPerformed(ActionEvent)} was called.
 * 
 * @see Action
 */
public interface DelegatedCallbackAction extends Action {

	/**
	 * Sets the parent {@link Action} to which we delegate all methods.
	 */
	void setParentAction(Action parentAction);

	/**
	 * Sets the {@link Runnable callback} that will be called if the method
	 * {@link #actionPerformed(ActionEvent)} is called..
	 */
	void setCallback(Runnable callback);

	/**
	 * Calls first the {@link Runnable callback} and then delegates to the
	 * parent {@link Action}.
	 */
	@Override
	public void actionPerformed(ActionEvent e);
}
