package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import static info.clearthought.layout.TableLayoutConstants.FILL;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.threads.api.Threads;
import com.anrisoftware.prefdialog.miscswing.actions.Actions;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.multichart.actions.PanelActions;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.AxisNegative;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation;
import com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

public class MultiChartPanel implements ChartPanel {

    private final Map<String, Chart> chartsMap;

    private final List<Chart> charts;

    @Inject
    private UiControlPanel panel;

    @Inject
    private ToolbarActions toolbarActions;

    @Inject
    private PanelActions panelActions;

    @Inject
    private Actions actions;

    private boolean antiAliasing;

    private boolean blackWhite;

    private boolean showShapes;

    @Inject
    @OnAwt
    MultiChartPanel() {
        this.chartsMap = new HashMap<String, Chart>();
        this.charts = new ArrayList<Chart>();
    }

    @OnAwt
    @Override
    public ChartPanel createPanel() {
        panel.setAction(panel.getAutoZoomButton(), toolbarActions);
        panel.setAction(panel.getZoomInButton(), toolbarActions);
        panel.setAction(panel.getZoomOutButton(), toolbarActions);
        panel.setAction(panel.getOptionsButton(), toolbarActions);
        setupGraphsPanel(panel.getGraphsPanel());
        panelActions.setToolbarActions(toolbarActions);
        panelActions.setChartPanel(this);
        return this;
    }

    @OnAwt
    @Override
    public void setThreads(Threads threads) {
        actions.setThreads(threads);
    }

    @OnAwt
    @Override
    public void setTexts(Texts texts) {
        toolbarActions.setTexts(texts);
        panel.getToolbarMenu().setTexts(texts);
    }

    @OnAwt
    @Override
    public void setImages(Images images) {
        toolbarActions.setImages(images);
        panel.getToolbarMenu().setImages(images);
    }

    @Override
    public Component getPanel() {
        return panel;
    }

    @OnAwt
    @Override
    public void setDomainAxisNegative(AxisNegative negative) {
        for (Chart chart : charts) {
            chart.setDomainAxisNegative(negative);
        }
    }

    @OnAwt
    @Override
    public void setPlotOrientation(PlotOrientation orientation) {
        for (Chart chart : charts) {
            chart.setPlotOrientation(orientation);
        }
    }

    @OnAwt
    @Override
    public void setAntiAliasing(boolean flag) {
        this.antiAliasing = flag;
        for (Chart chart : charts) {
            chart.setAntiAliasing(flag);
        }
    }

    @Override
    public boolean isAntiAliasing() {
        return antiAliasing;
    }

    @Override
    @OnAwt
    public void setBlackWhite(boolean flag) {
        this.blackWhite = flag;
        for (Chart chart : charts) {
            chart.setBlackWhite(flag);
        }
    }

    @Override
    public boolean isBlackWhite() {
        return blackWhite;
    }

    @Override
    @OnAwt
    public void setShowShapes(boolean flag) {
        this.showShapes = flag;
        for (Chart chart : charts) {
            chart.setShowShapes(flag);
        }
    }

    @Override
    public boolean isShowShapes() {
        return showShapes;
    }

    @Override
    @OnAwt
    public void setAutoZoomDomain(boolean flag) {
        for (Chart chart : charts) {
            chart.setAutoZoomDomain(flag);
        }
    }

    @Override
    public void setZoomDomain(int factor) {
        for (Chart chart : charts) {
            chart.setZoomDomain(factor);
        }
    }

    @Override
    public void setMaximumView(int maximum) {
        for (Chart chart : charts) {
            chart.setMaximumView(maximum);
        }
    }

    @OnAwt
    @Override
    public void addChart(Chart chart) {
        String name = chart.getName();
        if (!chartsMap.containsKey(name)) {
            chartsMap.put(name, chart);
            charts.add(chart);
            addChartPanel(chart);
            if (chartsMap.size() == 1) {
                toolbarActions.setActionsEnabled(true);
            }
        }
    }

    @OnAwt
    @Override
    public void removeChart(Chart chart) {
        if (chartsMap.remove(chart.getName()) != null) {
            removeChartPanel(chart);
            if (chartsMap.size() == 0) {
                toolbarActions.setActionsEnabled(false);
            }
        }
    }

    @OnAwt
    @Override
    public void setIconsOnly(boolean flag) {
        panel.setIconsOnly(flag);
    }

    @OnAwt
    @Override
    public void setTextOnly(boolean flag) {
        panel.setTextOnly(flag);
    }

    @OnAwt
    @Override
    public void setIconSize(IconSize size) {
        panel.setIconSize(size);
    }

    private void setupGraphsPanel(JPanel panel) {
        double[] cols = { FILL };
        double[] rows = { FILL };
        panel.setLayout(new TableLayout(cols, rows));
    }

    private void addChartPanel(Chart chart) {
        JPanel graphsPanel = panel.getGraphsPanel();
        TableLayout layout = (TableLayout) graphsPanel.getLayout();
        int size = chartsMap.size();
        if (layout.getNumColumn() < size) {
            layout.insertColumn(layout.getNumColumn(), FILL);
        }
        graphsPanel.add(chart.getPanel(), format("%d, %d", size - 1, 0));
        layout.layoutContainer(graphsPanel);
        graphsPanel.repaint();
    }

    private void removeChartPanel(Chart chart) {
        JPanel graphsPanel = panel.getGraphsPanel();
        int index = charts.indexOf(chart);
        graphsPanel.remove(index);
        TableLayout layout = (TableLayout) graphsPanel.getLayout();
        layout.deleteColumn(index);
        layout.layoutContainer(graphsPanel);
        graphsPanel.repaint();
    }

}
