/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.actions;

import static javax.swing.SwingUtilities.invokeLater;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.threads.api.Threads;

/**
 * Executes actions either on the worker thread or the AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
public class Actions {

	private static class ActionEntry {

		public final Callable<?> action;

		public final PropertyChangeListener[] listeners;

		public ActionEntry(Callable<?> action,
				PropertyChangeListener[] listeners) {
			this.action = action;
			this.listeners = listeners;
		}

	}

	private final Map<String, List<ActionEntry>> actions;

	private final Map<String, List<Runnable>> awtActions;

	private Threads threads;

	Actions() {
		this.actions = new HashMap<String, List<ActionEntry>>();
		this.awtActions = new HashMap<String, List<Runnable>>();
	}

	/**
	 * Sets the threads to execute the actions.
	 * 
	 * @param threads
	 *            the {@link Threads}.
	 */
	public void setThreads(Threads threads) {
		this.threads = threads;
	}

	/**
	 * Adds a new action to be executed.
	 * 
	 * @param name
	 *            the name of the action.
	 * 
	 * @param action
	 *            the {@link Callable} action.
	 * 
	 * @param listeners
	 *            optionally, a list of {@link PropertyChangeListener}.
	 */
	public void addAction(String name, Callable<?> action,
			PropertyChangeListener... listeners) {
		actionEntries(name).add(new ActionEntry(action, listeners));
	}

	/**
	 * Adds a new action to be executed on the AWT thread.
	 * 
	 * @param name
	 *            the name of the action.
	 * 
	 * @param action
	 *            the {@link Runnable} action.
	 */
	public void addAWTAction(String name, Runnable action) {
		awtActionEntries(name).add(action);
	}

	/**
	 * Execute the action.
	 * 
	 * @param name
	 *            the name of the action.
	 */
	public void executeActions(String name) {
		if (actions.containsKey(name)) {
			for (ActionEntry entry : actionEntries(name)) {
				threads.submit(entry.action, entry.listeners);
			}
		} else {
			executeActionsonAWT(name);
		}
	}

	/**
	 * Execute the action on the AWT thread.
	 * 
	 * @param name
	 *            the name of the action.
	 */
	public void executeActionsonAWT(String name) {
		for (Runnable task : awtActionEntries(name)) {
			invokeLater(task);
		}
	}

	private List<ActionEntry> actionEntries(String name) {
		List<ActionEntry> entries = actions.get(name);
		if (entries == null) {
			entries = new ArrayList<Actions.ActionEntry>();
			actions.put(name, entries);
		}
		return entries;
	}

	private List<Runnable> awtActionEntries(String name) {
		List<Runnable> entries = awtActions.get(name);
		if (entries == null) {
			entries = new ArrayList<Runnable>();
			awtActions.put(name, entries);
		}
		return entries;
	}
}