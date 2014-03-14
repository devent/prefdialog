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

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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

    @Inject
    private DefaultOffsetNumberTickUnitFactory tickUnitFactory;

    private PropertyChangeSupport p;

    private ChartModel model;

    private ChartModelListener modelListener;

    private int maximumView;

    private PlotOrientation orientation;

    private boolean showShapes;

    private boolean blackWhite;

    private MouseWheelListener mouseScrollListener;

    private OffsetTickUnit domainTickUnit;

    /**
     * @see FreechartXYChartFactory#create(String, JFreeChart)
     */
    @Inject
    @OnAwt
    FreechartXYChart(@Assisted String name, @Assisted JFreeChart chart) {
        this.name = name;
        this.panel = new ChartPanel(chart);
        this.chart = chart;
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
        this.domainTickUnit = tickUnitFactory
                .create(model.getViewMaximum() / 10);
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
        model.setViewMaximum(max);
    }

    @OnAwt
    @Override
    public void setOffset(int offset) {
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
        int size = model.getRowCount();
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
        domainTickUnit.setOffset(model.getOffset());
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
        int columns = model.getColumnCount();
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
