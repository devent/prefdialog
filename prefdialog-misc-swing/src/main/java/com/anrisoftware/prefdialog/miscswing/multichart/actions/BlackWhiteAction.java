/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;

/**
 * Sets black/white or color data graph.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class BlackWhiteAction implements Runnable {

    private ChartPanel chartPanel;

    public void setChartPanel(ChartPanel panel) {
        this.chartPanel = panel;
    }

	@Override
	public void run() {
        boolean flag = chartPanel.isBlackWhite();
        chartPanel.setBlackWhite(!flag);
	}
}