package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import java.awt.Component;
import java.util.Map;

import com.anrisoftware.globalpom.threads.api.Threads;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

public interface ChartPanel {

    ChartPanel createPanel();

    Component getPanel();

    void setThreads(Threads threads);

    void setTexts(Texts texts);

    void setImages(Images images);

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

    boolean isAntiAliasing();

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

    boolean isBlackWhite();

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

    boolean isShowShapes();

    void setIconsOnly(boolean flag);

    void setTextOnly(boolean flag);

    void setTextAlongsideIcons(boolean flag);

    void setIconSize(IconSize size);

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
}
