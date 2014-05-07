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
package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.globalpom.threads.api.Threads;
import com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenuProperty;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

public interface ChartPanel {

    ChartPanel createPanel();

    Component getPanel();

    void setThreads(Threads threads);

    void setTexts(Texts texts);

    void setImages(Images images);

    void setName(String name);

    String getName();

    void addChart(Chart chart);

    void removeChart(Chart chart);

    /**
     * Returns the charts in the panel.
     * 
     * @return the {@link Map} of {@link Chart} charts with the chart name as
     *         the key.
     */
    Map<String, Chart> getCharts();

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
     * Returns if anti-aliasing is used in the data graph.
     * 
     * @return {@code true} if anti-aliasing is enabled.
     */
    boolean isAntiAliasing();

    /**
     * Sets black/white or color data graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to set black/white.
     */
    void setBlackWhite(boolean flag);

    /**
     * Returns black/white or color data graph.
     * 
     * @return {@code true} if black/white is set.
     */
    boolean isBlackWhite();

    /**
     * Sets show shapes graph.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable shapes.
     */
    void setShowShapes(boolean flag);

    /**
     * Returns shows shapes on the graph.
     * 
     * @return {@code true} if the shapes are shown.
     */
    boolean isShowShapes();

    /**
     * Sets the icon size for the actions.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param size
     *            the {@link IconSize}.
     */
    void setIconSize(IconSize size);

    /**
     * Returns the icon size for the actions.
     * 
     * @return the {@link IconSize}.
     */
    IconSize getIconSize();

    /**
     * Sets text position for actions.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param position
     *            the {@link TextPosition}.
     */
    void setTextPosition(TextPosition position);

    /**
     * Returns the text position for actions.
     * 
     * @return the {@link TextPosition}.
     */
    TextPosition getTextPosition();

    void setAutoZoomDomain(boolean flag);

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
     * @param min
     *            the minimum value.
     * 
     * @param max
     *            the maximum value.
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
     * Sets allowing domain axis scroll.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable domain axis scroll.
     */
    void setAllowDomainAxisScroll(boolean flag);

    /**
     * Sets allowing range axis scroll.
     * 
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param flag
     *            set to {@code true} to enable range axis scroll.
     */
    void setAllowRangeAxisScroll(boolean flag);

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
     * @see PropertyChangeSupport#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    void addPropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener);

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    void removePropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener);

}
