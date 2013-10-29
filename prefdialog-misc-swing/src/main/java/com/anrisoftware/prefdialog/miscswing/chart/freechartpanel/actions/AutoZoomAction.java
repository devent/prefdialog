/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuAction;

@SuppressWarnings("serial")
class AutoZoomAction extends AbstractMenuAction {

	private static final String NAME = "zoom_auto_tool_action";

	AutoZoomAction() {
		super(NAME);
		setEnabled(false);
	}
}
