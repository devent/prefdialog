/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractExecuteAction;

@SuppressWarnings("serial")
class AutoZoomDomainAction extends AbstractExecuteAction {

    private static final String NAME = "zoom_domain_auto_tool_action";

	AutoZoomDomainAction() {
		super(NAME);
		setEnabled(false);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
