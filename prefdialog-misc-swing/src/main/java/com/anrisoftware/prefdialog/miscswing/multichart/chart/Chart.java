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
package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.ExecutorService;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;

public interface Chart {

    String getName();

    void setThreadPool(ExecutorService pool);

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

    /**
     * Returns the chart. The return value must be casted to the specific chart
     * type.
     * 
     * @return the {@link Object} chart.
     */
    Object getChart();

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
     * @param flag
     *            set to {@code true} for auto zoom.
     */
    void setAutoZoomDomain(boolean flag);

    /**
     * Sets auto zooms of the range axis.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} for auto zoom.
     */
    void setAutoZoomRange(boolean flag);

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
     * Sets the range of the range axis. If the range is set then the zoom will
     * not exceed it.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param lower
     *            the lower range value.
     * 
     * @param upper
     *            the upper range value.
     */
    void setRangeAxisRange(double lower, double upper);

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
