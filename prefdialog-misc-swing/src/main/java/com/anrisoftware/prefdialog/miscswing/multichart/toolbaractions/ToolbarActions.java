/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        enable.add(AUTO_ZOOM_DOMAIN_NAME);
        enable.add(AUTO_ZOOM_RANGE_NAME);
        enable.add(ZOOM_IN_NAME);
        enable.add(ZOOM_OUT_NAME);
        enable.add(OPTIONS_NAME);
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
        actions.put(AUTO_ZOOM_DOMAIN_NAME, autoZoomDomainAction);
        actions.put(AUTO_ZOOM_RANGE_NAME, autoZoomRangeAction);
        actions.put(ZOOM_IN_NAME, zoomInAction);
        actions.put(ZOOM_OUT_NAME, zoomOutAction);
        actions.put(OPTIONS_NAME, optionsAction);
        return actions;
    }
}
