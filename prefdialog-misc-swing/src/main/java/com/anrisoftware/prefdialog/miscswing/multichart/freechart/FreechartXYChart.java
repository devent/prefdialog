/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.MODEL_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty.OFFSET_PROPERTY;
import static java.lang.Math.min;
import static java.lang.Math.round;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.colorpalette.PaletteFactory;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.ChartProperty;
import com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelListener;
import com.google.inject.assistedinject.Assisted;

/**
 * {@link XYSeries} chart.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class FreechartXYChart implements Chart {

    private static final String NAME = "name";

    private final String name;

    private final ChartPanel panel;

    private final JFreeChart chart;

    @Inject
    private FreechartXYChartLogger log;

    @Inject
    private PaletteFactory paletteFactory;

    private TickUnitFactory tickUnitFactory;

    private PropertyChangeSupport p;

    private ChartModel model;

    private ChartModelListener modelListener;

    private int maximumView;

    private PlotOrientation orientation;

    private boolean showShapes;

    private boolean blackWhite;

    private MouseWheelListener mouseScrollListener;

    private OffsetTickUnit domainTickUnit;

    private final int forkLimitCount;

    private ExecutorService pool;

    private Range rangeAxis;

    /**
     * @see FreechartXYChartFactory#create(String, JFreeChart)
     */
    @Inject
    @OnAwt
    FreechartXYChart(@Assisted String name, @Assisted JFreeChart chart) {
        this.name = name;
        this.panel = new ChartPanel(chart);
        this.chart = chart;
        this.forkLimitCount = 2048;
        this.rangeAxis = null;
        resolveObject();
    }

    private Object resolveObject() {
        this.p = new PropertyChangeSupport(this);
        this.mouseScrollListener = new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (model == null) {
                    return;
                }
                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    int offset = model.getOffset();
                    setOffset(offset + e.getUnitsToScroll());
                }
            }
        };
        this.modelListener = new ChartModelListener() {

            @Override
            public void chartChanged(ChartModelEvent e) {
                updateChart(e);
            }
        };
        return this;
    }

    @Inject
    public void setDefaultOffsetNumberTickUnitFactory(
            DefaultOffsetNumberTickUnitFactory factory) {
        this.tickUnitFactory = factory;
    }

    public void setTickUnitFactory(TickUnitFactory factory) {
        this.tickUnitFactory = factory;
    }

    @Override
    public void setThreadPool(ExecutorService pool) {
        this.pool = pool;
    }

    @OnAwt
    @Override
    public void setModel(ChartModel model) {
        ChartModel oldValue = this.model;
        if (oldValue == model) {
            return;
        }
        if (oldValue != null) {
            oldValue.removeChartModelListener(modelListener);
        }
        this.model = model;
        this.domainTickUnit = tickUnitFactory.create(model,
                model.getViewMaximum() / 10);
        model.addChartModelListener(modelListener);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setTickUnit((NumberTickUnit) domainTickUnit);
        setupNewModel();
        p.firePropertyChange(MODEL_PROPERTY.toString(), oldValue, model);
    }

    @Override
    public ChartModel getModel() {
        return model;
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
    public JFreeChart getChart() {
        return chart;
    }

    /**
     * @see ChartModel#setViewMaximum(int)
     */
    @OnAwt
    public void setViewMaximum(int max) {
        if (model == null) {
            return;
        }
        model.setViewMaximum(max);
        this.domainTickUnit = tickUnitFactory.create(model, max / 10);
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setTickUnit((NumberTickUnit) domainTickUnit);
        domainTickUnit.setOffset(model.getOffset());
    }

    @OnAwt
    @Override
    public void setOffset(int offset) {
        if (model == null) {
            return;
        }
        int oldValue = model.getOffset();
        model.setOffset(offset);
        p.firePropertyChange(OFFSET_PROPERTY.toString(), oldValue, offset);
    }

    @OnAwt
    @Override
    public void setAntiAliasing(boolean antiAliasing) {
        chart.setAntiAlias(antiAliasing);
    }

    @OnAwt
    @Override
    public void setBlackWhite(boolean flag) {
        boolean oldValue = this.blackWhite;
        if (oldValue != flag) {
            this.blackWhite = flag;
        }
    }

    @OnAwt
    @Override
    public void setShowShapes(boolean flag) {
        boolean oldValue = this.showShapes;
        if (oldValue != flag) {
            this.showShapes = flag;
            XYPlot plot = chart.getXYPlot();
            plot.setRenderer(createLineShapeRenderer());
        }
    }

    @OnAwt
    @Override
    public void setAutoZoomDomain(boolean flag) {
        if (!flag) {
            return;
        }
        ChartModel model = getModel();
        ChartPanel panel = this.panel;
        panel.restoreAutoDomainBounds();
        int size = model == null ? 0 : model.getRowCount();
        size = min(size / 4, maximumView);
        setViewMaximum(size);
    }

    @OnAwt
    @Override
    public void setAutoZoomRange(boolean flag) {
        if (!flag) {
            return;
        }
        ChartPanel panel = this.panel;
        panel.restoreAutoRangeBounds();
        if (rangeAxis != null) {
            chart.getXYPlot().getRangeAxis().setRange(rangeAxis);
        }
    }

    @OnAwt
    @Override
    public void setZoomDomain(int factor) {
        ChartModel model = getModel();
        int size = model.getViewMaximum();
        float zoom = factor < 0 ? 1.25f : 0.75f;
        size = round(size * zoom);
        setViewMaximum(size);
    }

    @Override
    @OnAwt
    public void setRangeAxisRange(double lower, double upper) {
        this.rangeAxis = new Range(lower, upper);
        setAutoZoomRange(true);
    }

    @Override
    public void setMaximumView(int maximum) {
        this.maximumView = maximum;
    }

    @OnAwt
    @Override
    public void setPlotOrientation(PlotOrientation orientation) {
        PlotOrientation oldValue = this.orientation;
        if (oldValue == orientation) {
            return;
        }
        this.orientation = orientation;
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(toFreechartOrientation(orientation));
    }

    @OnAwt
    @Override
    public void setAllowMouseScroll(boolean flag) {
        if (flag) {
            panel.addMouseWheelListener(mouseScrollListener);
        } else {
            panel.removeMouseWheelListener(mouseScrollListener);
        }
    }

    @Override
    public void addPropertyChangeListener(ChartProperty property,
            PropertyChangeListener listener) {
        p.addPropertyChangeListener(property.toString(), listener);
    }

    @Override
    public void removePropertyChangeListener(ChartProperty property,
            PropertyChangeListener listener) {
        p.removePropertyChangeListener(property.toString(), listener);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(NAME, name).toString();
    }

    private void setupNewModel() {
        setupData(0, model.getMaximumRowCount());
        updateInsertData(0, model.getMaximumRowCount() - 1, 0);
        setMaximumView(maximumView / model.getColumnCount());
    }

    private void setupData(int row0, int row1) {
        ChartModel model = this.model;
        int cols = model.getColumnCount();
        XYSeriesCollection dataset = getCategory();
        dataset.removeAllSeries();
        for (int i = 0; i < cols; i++) {
            XYSeries series = new XYSeries(model.getColumnName(i), false, false);
            dataset.addSeries(series);
        }
    }

    private void updateChart(ChartModelEvent e) {
        int row0 = e.getFirstRow();
        int row1 = e.getLastRow();
        int offset = e.getOffset();
        log.chartChanged(this, e);
        domainTickUnit.setOffset(offset);
        switch (e.getType()) {
        case INSERTED:
            updateInsertData(row0, row1, offset);
            break;
        case DELETED:
            updateDeletedData(row0, row1, offset);
            break;
        case UPDATED:
            updateData(row0, row1, offset);
            break;
        }
    }

    private void updateData(int row0, int row1, int offset) {
        if (row0 >= model.getViewMaximum() || row0 >= model.getRowCount()) {
            return;
        }
        XYSeriesCollection series = getCategory();
        int max = series.getSeriesCount() * (row1 - row0);
        if (pool != null && max > forkLimitCount) {
            updateDataThread(row0, row1, offset, max);
        } else {
            updateData0(row0, row1, offset);
        }
    }

    private void updateData0(int row0, int row1, int offset) {
        ChartModel model = this.model;
        XYSeriesCollection series = getCategory();
        int col0 = 0;
        int col1 = series.getSeriesCount() - 1;
        for (int col = col0; col <= col1; col++) {
            XYSeries xyseries = series.getSeries(col);
            for (int row = row0; row <= row1; row++) {
                double value = model.getValueAt(row + offset, col);
                xyseries.updateByIndex(row, value);
            }
        }
    }

    private void updateDataThread(int row0, int row1, int offset, int max) {
        ((ForkJoinPool) pool)
                .invoke(new UpdateDataTask(row0, row1, offset, max));
    }

    @SuppressWarnings("serial")
    private class UpdateDataTask extends RecursiveAction {

        private final int row0;

        private final int row1;

        private final int offset;

        private final int max;

        public UpdateDataTask(int row0, int row1, int offset, int max) {
            this.row0 = row0;
            this.row1 = row1;
            this.offset = offset;
            this.max = max;
        }

        @Override
        protected void compute() {
            if (max < 256) {
                updateData0(row0, row1, offset);
            } else {
                int max = this.max / 2;
                int half = (row1 - row0) / 2;
                invokeAll(new UpdateDataTask(row0, row0 + half, offset, max),
                        new UpdateDataTask(row0 + half + 1, row1, offset, max));
            }
        }

    }

    private void updateDeletedData(int row0, int row1, int offset) {
        XYSeriesCollection series = getCategory();
        for (int col = 0; col < series.getSeriesCount(); col++) {
            XYSeries xyseries = series.getSeries(col);
            for (int row = row1; row >= row0; row--) {
                xyseries.remove(row);
            }
        }
    }

    private void updateInsertData(int row0, int row1, int offset) {
        ChartModel model = this.model;
        XYSeriesCollection series = getCategory();
        for (int col = 0; col < series.getSeriesCount(); col++) {
            XYSeries xyseries = series.getSeries(col);
            for (int row = row0; row <= row1; row++) {
                xyseries.add(row, model.getValueAt(row, col), false);
            }
            xyseries.fireSeriesChanged();
        }
    }

    private XYSeriesCollection getCategory() {
        return (XYSeriesCollection) panel.getChart().getXYPlot().getDataset();
    }

    private org.jfree.chart.plot.PlotOrientation toFreechartOrientation(
            PlotOrientation orientation) {
        switch (orientation) {
        case HORIZONTAL:
            return org.jfree.chart.plot.PlotOrientation.HORIZONTAL;
        case VERTICAL:
            return org.jfree.chart.plot.PlotOrientation.VERTICAL;
        }
        throw new UnsupportedOperationException();
    }

    private XYLineAndShapeRenderer createLineShapeRenderer() {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setBaseStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setDrawOutlines(true);
        int columns = model == null ? 1 : model.getColumnCount();
        if (blackWhite) {
            for (int i = 0; i < columns; i++) {
                renderer.setSeriesPaint(i, Color.black, false);
            }
        } else {
            Iterator<Color> it = paletteFactory.createBright().iterator();
            for (int i = 0; i < columns; i++, it.hasNext()) {
                renderer.setSeriesPaint(i, it.next(), false);
            }
        }
        if (!showShapes) {
            for (int i = 0; i < columns; i++) {
                renderer.setSeriesShapesVisible(i, false);
            }
        }
        renderer.notifyListeners(new RendererChangeEvent(renderer));
        return renderer;
    }

}
