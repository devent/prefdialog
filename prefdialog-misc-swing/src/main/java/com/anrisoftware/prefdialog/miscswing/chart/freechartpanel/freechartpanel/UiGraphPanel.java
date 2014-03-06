/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions.AUTO_ZOOM_NAME;
import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions.HORIZONTAL_SCROLLBAR_NAME;
import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions.VERTICAL_SCROLLBAR_NAME;
import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions.ZOOM_IN_NAME;
import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions.ZOOM_OUT_NAME;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;

import javax.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuActions;
import com.anrisoftware.prefdialog.miscswing.actions.MenuAction;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.actions.GraphWindowActions;
import com.anrisoftware.prefdialog.miscswing.chart.model.PlotOrientation;
import com.anrisoftware.prefdialog.miscswing.colorpalette.PaletteFactory;
import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu;

/**
 * Panel containing the forecast data graph.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class UiGraphPanel extends JPanel {

    @Inject
    private PaletteFactory paletteFactory;

    private final JScrollBar horizontalScrollBar;

    private final ChartPanel chartPanel;

    private final JFreeChart chart;

    private final JPanel graphScrollPanel;

    private final JFormattedTextField rangeField;

    private GraphScrollModel graphScrollModel;

    private final JToolBar toolBar;

    private final JButton zoomInButton;

    private final JButton zoomOutButton;

    private final JButton autoZoomButton;

    private final JButton optionsButton;

    private XYLineAndShapeRenderer lineShapeRenderer;

    private XYLineAndShapeRenderer originalLineShapeRenderer;

    private boolean shapeGraph;

    private boolean blackWhite;

    private ToolbarMenu toolbarMenu;
    private final JScrollBar verticalScrollBar;

    private JScrollBar plotScrollBar;

    /**
     * Create the panel.
     */
    UiGraphPanel() {
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        this.shapeGraph = true;
        this.blackWhite = false;
        setLayout(new BorderLayout(0, 0));

        this.chart = createChart();
        this.chartPanel = createChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

        graphScrollPanel = new JPanel();
        add(graphScrollPanel, BorderLayout.SOUTH);
        graphScrollPanel.setLayout(new BorderLayout(0, 0));

        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);

        zoomInButton = new JButton("Zoom In");
        zoomInButton.setName(ZOOM_IN_NAME);
        toolBar.add(zoomInButton);

        zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.setName(ZOOM_OUT_NAME);
        toolBar.add(zoomOutButton);

        autoZoomButton = new JButton("Auto Zoom");
        autoZoomButton.setName(AUTO_ZOOM_NAME);
        toolBar.add(autoZoomButton);

        optionsButton = new JButton("Options");
        toolBar.add(optionsButton);

        rangeField = new JFormattedTextField();
        toolBar.add(rangeField);
        rangeField.setValue(0);
        rangeField.setHorizontalAlignment(SwingConstants.TRAILING);

        verticalScrollBar = new JScrollBar();
        add(verticalScrollBar, BorderLayout.EAST);
        verticalScrollBar.setName(VERTICAL_SCROLLBAR_NAME);

        this.horizontalScrollBar = new JScrollBar();
        add(horizontalScrollBar, BorderLayout.SOUTH);
        horizontalScrollBar.setName(HORIZONTAL_SCROLLBAR_NAME);
        horizontalScrollBar.setOrientation(JScrollBar.HORIZONTAL);

        setPlotOrientation(PlotOrientation.VERTICAL);
    }

    private JFreeChart createChart() {
        String title = null;
        String xAxisLabel = null;
        String yAxisLabel = null;
        XYSeriesCollection dataset = new XYSeriesCollection();
        org.jfree.chart.plot.PlotOrientation orientation = org.jfree.chart.plot.PlotOrientation.VERTICAL;
        boolean legend = true;
        boolean tooltips = true;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel,
                yAxisLabel, dataset, orientation, legend, tooltips, urls);
        setupPlot(chart);
        integerTicks(chart);
        return chart;
    }

    private XYPlot setupPlot(JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        return plot;
    }

    private void integerTicks(JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    private ChartPanel createChartPanel(JFreeChart chart) {
        ChartPanel panel = new ChartPanel(chart);
        panel.setPopupMenu(null);
        panel.setRangeZoomable(false);
        return panel;
    }

    @Inject
    void setPaletteFactory(PaletteFactory factory) {
        this.paletteFactory = factory;
        setupLineAndShape();
    }

    private void setupLineAndShape() {
        this.lineShapeRenderer = createLineShapeRenderer();
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(lineShapeRenderer);
    }

    public void setGraphScrollModel(GraphScrollModel model) {
        removeOldScrollModel(this.graphScrollModel);
        this.graphScrollModel = model;
        horizontalScrollBar.setModel(model);
        verticalScrollBar.setModel(model);
    }

    private void removeOldScrollModel(GraphScrollModel model) {
        if (model == null) {
            return;
        }
        horizontalScrollBar.setModel(new DefaultBoundedRangeModel());
        verticalScrollBar.setModel(new DefaultBoundedRangeModel());
    }

    public GraphScrollModel getGraphScrollModel() {
        return graphScrollModel;
    }

    @Inject
    void setToolbarMenu(ToolbarMenu toolbarMenu) {
        this.toolbarMenu = toolbarMenu;
        toolbarMenu.setToolBar(toolBar);
    }

    public ToolbarMenu getToolbarMenu() {
        return toolbarMenu;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public XYLineAndShapeRenderer getLineShapeRenderer() {
        return lineShapeRenderer;
    }

    public XYLineAndShapeRenderer getOriginalLineShapeRenderer() {
        return originalLineShapeRenderer;
    }

    public JScrollBar getGraphScrollBar() {
        return horizontalScrollBar;
    }

    public JFormattedTextField getRangeField() {
        return rangeField;
    }

    public JButton getAutoZoomButton() {
        return autoZoomButton;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public JButton getOptionsButton() {
        return optionsButton;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setActions(AbstractButton button, AbstractMenuActions actions) {
        String name = button.getName();
        button.setAction((Action) actions.getActions().get(name));
    }

    public void setBlackWhite(boolean blackWhite) {
        this.blackWhite = blackWhite;
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(createLineShapeRenderer());
    }

    public void setShapeGraph(boolean shape) {
        this.shapeGraph = shape;
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(createLineShapeRenderer());
    }

    private XYLineAndShapeRenderer createLineShapeRenderer() {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setBaseStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setDrawOutlines(true);
        if (blackWhite) {
            for (int i = 0; i < 64; i++) {
                renderer.setSeriesPaint(i, Color.black, false);
            }
        } else {
            Iterator<Color> it = paletteFactory.createBright().iterator();
            for (int i = 0; i < 64; i++, it.hasNext()) {
                renderer.setSeriesPaint(i, it.next(), false);
            }
        }
        if (!shapeGraph) {
            for (int i = 0; i < 64; i++) {
                renderer.setSeriesShapesVisible(i, false);
            }
        }
        renderer.notifyListeners(new RendererChangeEvent(renderer));
        return renderer;
    }

    public void setActions(final GraphWindowActions actions) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                setAction(zoomOutButton, actions);
                setAction(zoomInButton, actions);
                setAction(autoZoomButton, actions);
            }
        });
    }

    private void setAction(AbstractButton button, AbstractMenuActions actions) {
        String name = button.getName();
        MenuAction menuAction = actions.getActions().get(name);
        Action action = (Action) menuAction;
        button.setAction(action);
        toolbarMenu.addAction((AbstractResourcesAction) menuAction);
    }

    @OnAwt
    public void setPlotOrientation(PlotOrientation orientation) {
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(toFreechartOrientation(orientation));
        switch (orientation) {
        case HORIZONTAL:
            verticalScrollBar.setVisible(true);
            horizontalScrollBar.setVisible(false);
            this.plotScrollBar = verticalScrollBar;
            break;
        case VERTICAL:
            verticalScrollBar.setVisible(false);
            horizontalScrollBar.setVisible(true);
            this.plotScrollBar = horizontalScrollBar;
            break;
        }
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

    public JScrollBar getVerticalScrollBar() {
        return verticalScrollBar;
    }

    public JScrollBar getPlotScrollBar() {
        return plotScrollBar;
    }
}
