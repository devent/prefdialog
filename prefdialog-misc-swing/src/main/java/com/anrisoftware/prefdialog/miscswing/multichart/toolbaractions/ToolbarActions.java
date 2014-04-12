/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuActions;
import com.anrisoftware.prefdialog.miscswing.actions.MenuAction;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Graph control panel actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ToolbarActions extends AbstractMenuActions {

    private static final String ID_PATTERN = "%s-%s";
    public static final String AUTO_ZOOM_DOMAIN_NAME = "graphWindow-autoZoomDomainButton";
    public static final String AUTO_ZOOM_RANGE_NAME = "graphWindow-autoZoomRangeButton";
    public static final String ZOOM_IN_NAME = "graphWindow-zoomInButton";
    public static final String ZOOM_OUT_NAME = "graphWindow-zoomOutButton";
    public static final String OPTIONS_NAME = "graphWindow-optionsButton";
    public static final String VERTICAL_SCROLLBAR_NAME = "graphWindow-verticalScrollbar";
    public static final String HORIZONTAL_SCROLLBAR_NAME = "graphWindow-horizontalScrollbar";

    @Inject
    private ZoomInAction zoomInAction;

    @Inject
    private ZoomOutAction zoomOutAction;

    @Inject
    private AutoZoomDomainAction autoZoomDomainAction;

    @Inject
    private AutoZoomRangeAction autoZoomRangeAction;

    @Inject
    private OptionsAction optionsAction;

    private Map<String, MenuAction> actions;

    public void setId(String id) {
        autoZoomDomainAction
                .setId(format(ID_PATTERN, AUTO_ZOOM_DOMAIN_NAME, id));
        autoZoomRangeAction.setId(format(ID_PATTERN, AUTO_ZOOM_RANGE_NAME, id));
        zoomInAction.setId(format(ID_PATTERN, ZOOM_IN_NAME, id));
        zoomOutAction.setId(format(ID_PATTERN, ZOOM_OUT_NAME, id));
        optionsAction.setId(format(ID_PATTERN, OPTIONS_NAME, id));
    }

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
        setActionEnabled(enabled, AUTO_ZOOM_DOMAIN_NAME, AUTO_ZOOM_RANGE_NAME,
                ZOOM_IN_NAME, ZOOM_OUT_NAME, OPTIONS_NAME);
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
        actions.put(AUTO_ZOOM_DOMAIN_NAME, autoZoomDomainAction);
        actions.put(AUTO_ZOOM_RANGE_NAME, autoZoomRangeAction);
        actions.put(ZOOM_IN_NAME, zoomInAction);
        actions.put(ZOOM_OUT_NAME, zoomOutAction);
        actions.put(OPTIONS_NAME, optionsAction);
        return actions;
    }
}
