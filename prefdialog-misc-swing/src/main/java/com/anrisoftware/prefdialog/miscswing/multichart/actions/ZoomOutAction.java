/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;

/**
 * Zoom out the domain axis graph.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ZoomOutAction implements Runnable {

    private ChartPanel chartPanel;

    public void setChartPanel(ChartPanel panel) {
        this.chartPanel = panel;
    }

	@Override
    public void run() {
        chartPanel.setZoomDomain(-1);
	}
}
