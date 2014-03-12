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

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.multichart.actions.PanelActions;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.AxisNegative;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

public class MultiChartPanel {

    private final Map<String, Chart> chartsMap;

    private final List<Chart> charts;

    @Inject
    private UiControlPanel panel;

    @Inject
    private PanelActions panelActions;

    @Inject
    @OnAwt
    MultiChartPanel() {
        this.chartsMap = new HashMap<String, Chart>();
        this.charts = new ArrayList<Chart>();
    }

    @OnAwt
    public MultiChartPanel createPanel() {
        panel.setAction(panel.getAutoZoomButton(), panelActions);
        panel.setAction(panel.getZoomInButton(), panelActions);
        panel.setAction(panel.getZoomOutButton(), panelActions);
        panel.setAction(panel.getOptionsButton(), panelActions);
        setupGraphsPanel(panel.getGraphsPanel());
        return this;
    }

    @OnAwt
    public void setTexts(Texts texts) {
        panelActions.setTexts(texts);
        panel.getToolbarMenu().setTexts(texts);
    }

    @OnAwt
    public void setImages(Images images) {
        panelActions.setImages(images);
        panel.getToolbarMenu().setImages(images);
    }

    public Component getPanel() {
        return panel;
    }

    /**
     * Sets the domain axis to be negative.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param negative
     *            the {@link AxisNegative}.
     */
    @OnAwt
    public void setDomainAxisNegative(AxisNegative negative) {
        for (Chart chart : charts) {
            chart.setDomainAxisNegative(negative);
        }
    }

    /**
     * Sets the orientation of the chart.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param orientation
     *            the {@link PlotOrientation}.
     */
    @OnAwt
    public void setPlotOrientation(PlotOrientation orientation) {
        for (Chart chart : charts) {
            chart.setPlotOrientation(orientation);
        }
    }

    /**
     * Sets to use anti-aliasing in the data graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable anti-aliasing.
     */
    @OnAwt
    public void setAntiAliasing(boolean flag) {
        for (Chart chart : charts) {
            chart.setAntiAliasing(flag);
        }
    }

    /**
     * Sets black/white or color data graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable black/white.
     */
    public void setBlackWhite(boolean flag) {
        for (Chart chart : charts) {
            chart.setBlackWhite(flag);
        }
    }

    /**
     * Sets show shapes graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable black/white.
     */
    public void setShowShapes(boolean flag) {
        for (Chart chart : charts) {
            chart.setShowShapes(flag);
        }
    }

    @OnAwt
    public void addChart(Chart chart) {
        String name = chart.getName();
        if (!chartsMap.containsKey(name)) {
            chartsMap.put(name, chart);
            charts.add(chart);
            addChartPanel(chart);
            if (chartsMap.size() == 1) {
                panelActions.setActionsEnabled(true);
            }
        }
    }

    @OnAwt
    public void removeChart(Chart chart) {
        if (chartsMap.remove(chart.getName()) != null) {
            removeChartPanel(chart);
            if (chartsMap.size() == 0) {
                panelActions.setActionsEnabled(false);
            }
        }
    }

    @OnAwt
    public void setIconsOnly(boolean b) {
        panel.setIconsOnly(b);
    }

    @OnAwt
    public void setTextOnly(boolean b) {
        panel.setTextOnly(b);
    }

    @OnAwt
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
