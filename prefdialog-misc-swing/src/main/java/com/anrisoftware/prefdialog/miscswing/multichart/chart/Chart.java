package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import java.awt.Component;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;

public interface Chart {

    String getName();

    /**
     * Sets the chart model.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param model
     *            the {@link ChartModel}.
     */
    void setModel(ChartModel model);

    ChartModel getModel();

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
    void setPlotOrientation(PlotOrientation orientation);

    Component getPanel();

    Object getChart();

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
    void setDomainAxisNegative(AxisNegative negative);

    /**
     * Returns the domain axis to be negative.
     * 
     * @return the {@link AxisNegative}.
     */
    AxisNegative getDomainAxisNegative();

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
    void setAntiAliasing(boolean flag);

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
    void setBlackWhite(boolean flag);

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
    void setShowShapes(boolean flag);

}
