/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Sets icon size menu action.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class IconSizeAction extends AbstractResourcesAction {

    private static final String ACTION_NAME = "icon_size_action";

	IconSizeAction() {
        super(ACTION_NAME);
        putValue(NAME, "Icon Size");
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
