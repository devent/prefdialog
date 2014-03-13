/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuAction;

@SuppressWarnings("serial")
class ZoomOutAction extends AbstractMenuAction {

	private static final String NAME = "zoom_out_tool_action";

	ZoomOutAction() {
		super(NAME);
		setEnabled(false);
	}
}
