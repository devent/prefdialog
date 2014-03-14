/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuAction;

@SuppressWarnings("serial")
class AutoZoomDomainAction extends AbstractMenuAction {

    private static final String NAME = "zoom_domain_auto_tool_action";

	AutoZoomDomainAction() {
		super(NAME);
		setEnabled(false);
	}
}
