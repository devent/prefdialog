/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuAction;

@SuppressWarnings("serial")
class OptionsAction extends AbstractMenuAction {

    private static final String NAME = "options_tool_action";

	OptionsAction() {
		super(NAME);
		setEnabled(false);
	}
}
