/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuActions;
import com.anrisoftware.prefdialog.miscswing.actions.MenuAction;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Graph window menu actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GraphWindowActions extends AbstractMenuActions {

	public static final String AUTO_ZOOM_NAME = "autoZoomButton";
	public static final String ZOOM_IN_NAME = "zoomInButton";
	public static final String ZOOM_OUT_NAME = "zoomOutButton";

	@Inject
	private ZoomInAction zoomInAction;

	@Inject
	private ZoomOutAction zoomOutAction;

	@Inject
	private AutoZoomAction autoZoomAction;

	private Map<String, MenuAction> actions;

	/**
	 * Enables the graph window actions.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param enabled
	 *            set to {@code true} to enable the window actions.
	 */
	@OnAwt
	public void setActionsEnabled(boolean enabled) {
		List<String> enable = new ArrayList<String>();
		enable.add(AUTO_ZOOM_NAME);
		enable.add(ZOOM_IN_NAME);
		enable.add(ZOOM_OUT_NAME);
		setActionsEnabled(enable.toArray(new String[0]), enabled);
	}

	@Override
	public Map<String, MenuAction> getActions() {
		if (actions == null) {
			actions = createActions();
		}
		return actions;
	}

	private Map<String, MenuAction> createActions() {
		actions = new HashMap<String, MenuAction>();
		actions.put(AUTO_ZOOM_NAME, autoZoomAction);
		actions.put(ZOOM_IN_NAME, zoomInAction);
		actions.put(ZOOM_OUT_NAME, zoomOutAction);
		return actions;
	}
}
