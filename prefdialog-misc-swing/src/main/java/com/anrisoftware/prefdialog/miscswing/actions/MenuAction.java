/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.actions;

import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

/**
 * Menu action.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface MenuAction {

	/**
	 * Adds the action to be run.
	 * 
	 * @param action
	 *            the {@link Callable} action.
	 * 
	 * @param listeners
	 *            the {@link PropertyChangeListener} listeners that are informed
	 *            when the status of the action have changed.
	 */
	void addAction(Callable<?> action, PropertyChangeListener... listeners);

	/**
	 * Adds the action to be run on the AWT thread.
	 * 
	 * @param action
	 *            the {@link Runnable} action.
	 */
	void addAWTAction(Runnable action);

}
