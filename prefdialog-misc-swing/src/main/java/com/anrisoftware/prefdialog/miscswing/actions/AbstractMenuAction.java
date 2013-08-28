/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Sets the action name and mnemonic from text resource.
 * <p>
 * <h2>Example</h2>
 * <p>
 * 
 * <pre>
 * &#064;SuppressWarnings(&quot;serial&quot;)
 * class ExitAction extends AbstractMenuAction {
 * 
 * 	private static final String NAME = &quot;exit_action&quot;;
 * 
 * 	ExitAction() {
 * 		super(NAME);
 * 		setEnabled(true);
 * 	}
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractMenuAction extends AbstractResourcesAction
		implements MenuAction {

	private Actions actions;

	/**
	 * @see AbstractResourcesAction#AbstractResourcesAction(String)
	 */
	protected AbstractMenuAction(String name) {
		super(name);
	}

	/**
	 * Injects the application actions to submit actions to.
	 * 
	 * @param actions
	 *            the {@link Actions}.
	 */
	@Inject
	void setActions(Actions actions) {
		this.actions = actions;
	}

	@Override
	public void addAction(Callable<?> action,
			PropertyChangeListener... listeners) {
		actions.addAction(getName(), action, listeners);
	}

	@Override
	public void addAWTAction(Runnable action) {
		actions.addAWTAction(getName(), action);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actions.executeActions(getName());
	}
}