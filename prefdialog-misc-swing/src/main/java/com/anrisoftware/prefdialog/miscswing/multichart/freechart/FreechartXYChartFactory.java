package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import org.jfree.chart.JFreeChart;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

public interface FreechartXYChartFactory {

    @OnAwt
    FreechartXYChart create(String name, JFreeChart chart);
}
