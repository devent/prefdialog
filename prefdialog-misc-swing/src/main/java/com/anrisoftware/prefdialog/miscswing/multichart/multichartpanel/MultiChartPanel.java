/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener.lockedChangeListener;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener.lockedPropertyChangeListener;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.MODEL_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.OFFSET_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartUtils.FORK_JOIN_POOL_CLASS;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartUtils.haveClass;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation.HORIZONTAL;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation.VERTICAL;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static java.lang.Math.max;
import static java.lang.String.format;
import static java.util.Collections.unmodifiableMap;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.textposition.TextPosition;
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
import com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenuProperty;
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

    private LockedChangeListener horizontalScrollListener;

    private LockedChangeListener verticalScrollListener;

    private PropertyChangeListener chartModelListener;

    private LockedPropertyChangeListener chartOffsetListener;

    private ChartModel chartModel;

    private boolean antiAliasing;

    private boolean blackWhite;

    private boolean showShapes;

    private PlotOrientation plotOrientation;

    private boolean allowDomainAxisScroll;

    private boolean allowRangeAxisScroll;

    private int maximumView;

    private boolean allowMouseScroll;

    private String name;

    private ExecutorService threadPool;

    @Inject
    @OnAwt
    MultiChartPanel() {
        this.chartsMap = new HashMap<String, Chart>();
        this.charts = new LinkedList<Chart>();
        this.plotOrientation = PlotOrientation.VERTICAL;
        this.antiAliasing = true;
        this.blackWhite = false;
        this.showShapes = true;
        this.plotOrientation = VERTICAL;
        this.allowDomainAxisScroll = true;
        this.allowRangeAxisScroll = true;
        this.allowMouseScroll = true;
        this.maximumView = 100;
        if (haveClass(FORK_JOIN_POOL_CLASS)) {
            this.threadPool = new ForkJoinPool();
        }
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
        panel.setAction(panel.getAutoZoomDomainButton(), toolbarActions);
        panel.setAction(panel.getAutoZoomRangeButton(), toolbarActions);
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

    /**
     * Sets the name of the panel.
     *
     * @param name
     *            the {@link String} name.
     */
    @Override
    @OnAwt
    public void setName(String name) {
        this.name = name;
        toolbarActions.setId(name);
    }

    @Override
    public String getName() {
        return name;
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
    public void setRangeAxisRange(double lower, double upper) {
        for (Chart chart : charts) {
            chart.setRangeAxisRange(lower, upper);
        }
    }

    @Override
    @OnAwt
    public void setAutoZoomDomain(boolean flag) {
        for (Chart chart : charts) {
            chart.setAutoZoomDomain(flag);
        }
        if (chartModel != null) {
            updateDomainAxisMaximum();
        }
    }

    @Override
    public void setAutoZoomRange(boolean flag) {
        for (Chart chart : charts) {
            chart.setAutoZoomRange(flag);
        }
    }

    @Override
    @OnAwt
    public void setZoomDomain(int factor) {
        for (Chart chart : charts) {
            chart.setZoomDomain(factor);
        }
        if (chartModel != null) {
            updateDomainAxisMaximum();
        }
    }

    @Override
    @OnAwt
    public void setMaximumView(int maximum) {
        this.maximumView = maximum;
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

    @Override
    @OnAwt
    public void setTextPosition(TextPosition position) {
        panel.getToolbarMenu().setTextPosition(position);
    }

    @Override
    public TextPosition getTextPosition() {
        return panel.getToolbarMenu().getTextPosition();
    }

    @OnAwt
    @Override
    public void setIconSize(IconSize size) {
        panel.getToolbarMenu().setIconSize(size);
    }

    @Override
    public IconSize getIconSize() {
        return panel.getToolbarMenu().getIconSize();
    }

    @OnAwt
    @Override
    public void setAllowMouseScroll(boolean flag) {
        this.allowMouseScroll = flag;
        for (Chart chart : charts) {
            chart.setAllowMouseScroll(flag);
        }
    }

    @OnAwt
    @Override
    public void addChart(Chart chart) {
        String name = chart.getName();
        if (chartsMap.containsKey(name)) {
            return;
        }
        chart.setAllowMouseScroll(allowMouseScroll);
        chart.setPlotOrientation(plotOrientation);
        chart.setMaximumView(maximumView);
        chart.setAntiAliasing(antiAliasing);
        chart.setBlackWhite(blackWhite);
        chart.setShowShapes(showShapes);
        if (threadPool != null) {
            chart.setThreadPool(threadPool);
        }
        chartsMap.put(name, chart);
        charts.add(chart);
        addChartPropertyChangeListeners(chart);
        chartModel = chart.getModel();
        addChartPanel(chart);
        if (chartsMap.size() == 1) {
            toolbarActions.setActionsEnabled(true);
            setAutoZoomDomain(true);
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

    @Override
    public Map<String, Chart> getCharts() {
        return unmodifiableMap(chartsMap);
    }

    @Override
    public void addPropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener) {
        panel.getToolbarMenu().addPropertyChangeListener(property, listener);
    }

    @Override
    public void removePropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener) {
        panel.getToolbarMenu().removePropertyChangeListener(property, listener);
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
        charts.remove(index);
        graphsPanel.remove(index);
        TableLayout layout = (TableLayout) graphsPanel.getLayout();
        layout.deleteColumn(index);
        layout.layoutContainer(graphsPanel);
        graphsPanel.repaint();
    }

}
