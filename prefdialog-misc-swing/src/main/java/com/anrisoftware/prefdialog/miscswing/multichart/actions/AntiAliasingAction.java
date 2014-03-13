/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.actions;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;

/**
 * Sets the use of anti-aliasing in data graphs.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class AntiAliasingAction implements Runnable {

    private ChartPanel chartPanel;

    public void setChartPanel(ChartPanel panel) {
        this.chartPanel = panel;
    }

	@Override
    @OnAwt
	public void run() {
        boolean flag = chartPanel.isAntiAliasing();
        chartPanel.setAntiAliasing(!flag);
	}
}
