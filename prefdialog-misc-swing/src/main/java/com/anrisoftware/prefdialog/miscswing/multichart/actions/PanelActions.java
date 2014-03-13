/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prospect-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import static com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions.AUTO_ZOOM_NAME;
import static com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions.ZOOM_IN_NAME;
import static com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions.ZOOM_OUT_NAME;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;
import com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions;

/**
 * Chart panel actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class PanelActions {

    @Inject
    private AntiAliasingAction antiAliasingAction;

    @Inject
    private AutoZoomAction autoZoomAction;

    @Inject
    private BlackWhiteAction blackWhiteAction;

    @Inject
    private ShowShapesAction showShapesAction;

    @Inject
    private ZoomInAction zoomInAction;

    @Inject
    private ZoomOutAction zoomOutAction;

    /**
     * Sets the actions of the panel tool-bar.
     * 
     * @param actions
     *            the {@link ToolbarActions}.
     */
    public void setToolbarActions(ToolbarActions actions) {
        actions.addAWTAction(AUTO_ZOOM_NAME, autoZoomAction);
        actions.addAWTAction(ZOOM_IN_NAME, zoomInAction);
        actions.addAWTAction(ZOOM_OUT_NAME, zoomOutAction);
    }

    public void setChartPanel(ChartPanel panel) {
        antiAliasingAction.setChartPanel(panel);
        autoZoomAction.setChartPanel(panel);
        blackWhiteAction.setChartPanel(panel);
        showShapesAction.setChartPanel(panel);
        zoomInAction.setChartPanel(panel);
        zoomOutAction.setChartPanel(panel);
    }

}
