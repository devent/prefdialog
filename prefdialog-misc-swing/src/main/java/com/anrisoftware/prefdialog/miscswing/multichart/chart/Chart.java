package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    /**
     * Sets auto zooms of the domain axis.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param autoZoom
     *            set to {@code true} for auto zoom.
     */
    void setAutoZoomDomain(boolean flag);

    /**
     * Zooms the domain axis.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param factor
     *            set to 1 to zoom in or -1 to zoom out the domain axis.
     */
    void setZoomDomain(int factor);

    /**
     * Sets the maximum rows of the view for auto-zoom.
     * 
     * @param maximum
     *            the maximum rows.
     */
    void setMaximumView(int maximum);

    /**
     * Sets allow scrolling the domain axis by the mouse wheel.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to allow scrolling.
     */
    void setAllowMouseScroll(boolean flag);

    /**
     * Sets the offset of the data that is shown in the graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param offset
     *            the offset.
     */
    void setOffset(int offset);

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ChartProperty
     */
    void addPropertyChangeListener(ChartProperty property,
            PropertyChangeListener listener);

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ChartProperty
     */
    void removePropertyChangeListener(ChartProperty property,
            PropertyChangeListener listener);

}
