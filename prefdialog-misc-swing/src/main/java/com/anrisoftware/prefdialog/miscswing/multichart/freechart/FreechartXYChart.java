package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import java.awt.Component;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.google.inject.assistedinject.Assisted;

public class FreechartXYChart implements Chart {

    private static final String NAME = "name";

    private final String name;

    private final ChartPanel panel;

    private final JFreeChart chart;

    @Inject
    @OnAwt
    FreechartXYChart(@Assisted String name, @Assisted JFreeChart chart) {
        this.name = name;
        this.panel = new ChartPanel(chart);
        this.chart = chart;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Component getPanel() {
        return panel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(NAME, name).toString();
    }
}
