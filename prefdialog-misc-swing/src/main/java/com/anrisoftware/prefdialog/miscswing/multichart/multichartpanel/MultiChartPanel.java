package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener.lockedChangeListener;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener.lockedPropertyChangeListener;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.MODEL_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.OFFSET_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation.HORIZONTAL;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation.VERTICAL;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static java.lang.Math.max;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.threads.api.Threads;
import com.anrisoftware.prefdialog.miscswing.actions.Actions;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener;
import com.anrisoftware.prefdialog.miscswing.multichart.actions.PanelActions;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartPanel;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;
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

    private PlotOrientation plotOrientation;

    private LockedChangeListener horizontalScrollListener;

    private LockedChangeListener verticalScrollListener;

    private PropertyChangeListener chartModelListener;

    private ChartModel chartModel;

    private boolean allowDomainAxisScroll;

    private boolean allowRangeAxisScroll;

    private LockedPropertyChangeListener chartOffsetListener;

    @Inject
    @OnAwt
    MultiChartPanel() {
        this.chartsMap = new HashMap<String, Chart>();
        this.charts = new ArrayList<Chart>();
        this.plotOrientation = PlotOrientation.VERTICAL;
        this.allowDomainAxisScroll = true;
        this.allowRangeAxisScroll = true;
        readResolve();
    }

    private Object readResolve() {
        this.chartModelListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateChartModel((ChartModel) evt.getNewValue());
            }
        };
        this.horizontalScrollListener = lockedChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateHorizontalScroll(e);
            }
        });
        this.verticalScrollListener = lockedChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateVerticalScroll(e);
            }
        });
        this.chartOffsetListener = lockedPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                chartOffsetListener.lock();
                horizontalScrollListener.lock();
                verticalScrollListener.lock();
                updateChartOffset((Integer) evt.getNewValue());
                verticalScrollListener.unlock();
                horizontalScrollListener.unlock();
                chartOffsetListener.unlock();
            }
        });
        return this;
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
        setupScrollBars();
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
    public void setPlotOrientation(PlotOrientation orientation) {
        this.plotOrientation = orientation;
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
        updateDomainAxisMaximum();
    }

    @Override
    @OnAwt
    public void setZoomDomain(int factor) {
        for (Chart chart : charts) {
            chart.setZoomDomain(factor);
        }
        updateDomainAxisMaximum();
    }

    @Override
    @OnAwt
    public void setMaximumView(int maximum) {
        for (Chart chart : charts) {
            chart.setMaximumView(maximum);
        }
        getDomainAxisScroll().setMaximum(maximum);
    }

    @Override
    @OnAwt
    public void setAllowDomainAxisScroll(boolean flag) {
        this.allowDomainAxisScroll = flag;
        JScrollBar scroll = getDomainAxisScroll();
        scroll.setVisible(flag);
    }

    @Override
    @OnAwt
    public void setAllowRangeAxisScroll(boolean flag) {
        this.allowRangeAxisScroll = flag;
        JScrollBar scroll = getRangeAxisScroll();
        scroll.setVisible(flag);
    }

    public JScrollBar getRangeAxisScroll() {
        switch (plotOrientation) {
        case HORIZONTAL:
            return panel.getHorizontalScrollBar();
        case VERTICAL:
            return panel.getVerticalScrollBar();
        default:
            return null;
        }
    }

    public JScrollBar getDomainAxisScroll() {
        switch (plotOrientation) {
        case HORIZONTAL:
            return panel.getVerticalScrollBar();
        case VERTICAL:
            return panel.getHorizontalScrollBar();
        default:
            return null;
        }
    }

    @OnAwt
    @Override
    public void addChart(Chart chart) {
        String name = chart.getName();
        if (!chartsMap.containsKey(name)) {
            chartsMap.put(name, chart);
            charts.add(chart);
            addChartPropertyChangeListeners(chart);
            chartModel = chart.getModel();
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
            removeChartPropertyChangeListeners(chart);
            if (chartsMap.size() == 0) {
                toolbarActions.setActionsEnabled(false);
                chartModel = null;
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

    @OnAwt
    @Override
    public void setAllowMouseScroll(boolean flag) {
        for (Chart chart : charts) {
            chart.setAllowMouseScroll(flag);
        }
    }

    private void setupGraphsPanel(JPanel panel) {
        double[] cols = { FILL };
        double[] rows = { FILL };
        panel.setLayout(new TableLayout(cols, rows));
    }

    private void setupScrollBars() {
        panel.getHorizontalScrollBar().getModel().setExtent(0);
        panel.getHorizontalScrollBar().getModel()
                .addChangeListener(horizontalScrollListener);
        panel.getVerticalScrollBar().getModel().setExtent(0);
        panel.getVerticalScrollBar().getModel()
                .addChangeListener(verticalScrollListener);
    }

    private void updateChartModel(ChartModel model) {
        this.chartModel = model;
    }

    private void updateVerticalScroll(ChangeEvent e) {
        if (plotOrientation == HORIZONTAL && allowDomainAxisScroll) {
            updateModelOffset();
        }
    }

    private void updateHorizontalScroll(ChangeEvent e) {
        if (plotOrientation == VERTICAL && allowDomainAxisScroll) {
            updateModelOffset();
        }
    }

    private void updateModelOffset() {
        JScrollBar scroll = getDomainAxisScroll();
        int extent = scroll.getModel().getExtent();
        for (Chart chart : charts) {
            chart.getModel().setOffset(scroll.getValue() + extent);
        }
    }

    private void updateDomainAxisMaximum() {
        getDomainAxisScroll().setMaximum(
                max(chartModel.getRowCount() - chartModel.getViewMaximum(), 0));
    }

    private void updateChartOffset(int offset) {
        for (Chart chart : charts) {
            chart.setOffset(offset);
        }
        getDomainAxisScroll().setValue(offset);
    }

    private void addChartPropertyChangeListeners(Chart chart) {
        chart.addPropertyChangeListener(MODEL_PROPERTY, chartModelListener);
        chart.addPropertyChangeListener(OFFSET_PROPERTY, chartOffsetListener);
    }

    private void removeChartPropertyChangeListeners(Chart chart) {
        chart.removePropertyChangeListener(MODEL_PROPERTY, chartModelListener);
        chart.removePropertyChangeListener(OFFSET_PROPERTY, chartOffsetListener);
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