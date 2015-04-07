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
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import static com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions.AUTO_ZOOM_DOMAIN_NAME;
import static com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions.AUTO_ZOOM_RANGE_NAME;
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
    private AutoZoomDomainAction autoZoomDomainAction;

    @Inject
    private AutoZoomRangeAction autoZoomRangeAction;

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
        actions.addAWTAction(AUTO_ZOOM_DOMAIN_NAME, autoZoomDomainAction);
        actions.addAWTAction(AUTO_ZOOM_RANGE_NAME, autoZoomRangeAction);
        actions.addAWTAction(ZOOM_IN_NAME, zoomInAction);
        actions.addAWTAction(ZOOM_OUT_NAME, zoomOutAction);
    }

    public void setChartPanel(ChartPanel panel) {
        antiAliasingAction.setChartPanel(panel);
        autoZoomDomainAction.setChartPanel(panel);
        autoZoomRangeAction.setChartPanel(panel);
        blackWhiteAction.setChartPanel(panel);
        showShapesAction.setChartPanel(panel);
        zoomInAction.setChartPanel(panel);
        zoomOutAction.setChartPanel(panel);
    }

}
