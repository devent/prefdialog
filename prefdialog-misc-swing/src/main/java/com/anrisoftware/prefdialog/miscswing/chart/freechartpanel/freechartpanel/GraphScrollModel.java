/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.GraphScrollModel.Property.VALUE_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.GraphScrollModel.Property.VIEW_MAXIMUM_PROPERTY;
import static java.lang.Math.max;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.inject.Inject;
import javax.swing.DefaultBoundedRangeModel;

import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelListener;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the maximum range based on the forecast data.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class GraphScrollModel extends DefaultBoundedRangeModel {

    /**
     * Properties of the graph scroll model.
     * 
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 3.0
     */
    public static enum Property {

        /**
         * The current view position property.
         * 
         * @see #setValue(int)
         */
        VALUE_PROPERTY,

        /**
         * View maximum property.
         * 
         * @see #setViewMaximum(int)
         */
        VIEW_MAXIMUM_PROPERTY

    }

    private transient PropertyChangeSupport p;

    private transient ChartModelListener dataListener;

    private ChartModel model;

    private int viewMaximum;

    @Inject
    GraphScrollModel(@Assisted ChartModel model) {
        this.model = model;
        readResolve();
    }

    private Object readResolve() {
        this.p = new PropertyChangeSupport(this);
        this.dataListener = new ChartModelListener() {

            @Override
            public void chartChanged(ChartModelEvent e) {
                updateData();
            }
        };
        model.addChartModelListener(dataListener);
        return this;
    }

    /**
     * Removes the chart model from this scroll model.
     */
    public void uninstall() {
        ChartModel model = this.model;
        model.removeChartModelListener(dataListener);
        this.model = null;
    }

    /**
     * That is how many entries should be visible in the view.
     * <p>
     * <h2>Property</h2>
     * <p>
     * Notifies the property change listeners with the
     * {@link #VIEW_MAXIMUM_PROPERTY} property name.
     * 
     * @param max
     *            the view maximum.
     */
    public void setViewMaximum(int max) {
        int oldValue = this.viewMaximum;
        this.viewMaximum = max;
        if (oldValue != max) {
            updateData();
        }
        p.firePropertyChange(VIEW_MAXIMUM_PROPERTY.toString(), oldValue, max);
    }

    /**
     * <p>
     * <h2>Property</h2>
     * <p>
     * Notifies the property change listeners with the {@link #VALUE_PROPERTY}
     * property name.
     */
    @Override
    public void setValue(int n) {
        int oldValue = getValue();
        if (n < getMinimum()) {
            n = getMinimum();
        } else if (n > getMaximum()) {
            n = getMaximum();
        }
        super.setValue(n);
        p.firePropertyChange(VALUE_PROPERTY.toString(), oldValue, n);
    }

    private void updateData() {
        int max = model.getRowCount();
        max = max(max - viewMaximum, 0);
        int oldMax = getMaximum();
        if (max != oldMax) {
            setMaximum(max);
        }
    }

    /**
     * @see PropertyChangeSupportProperty#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see Property
     */
    public void addPropertyChangeListener(Property property,
            PropertyChangeListener listener) {
        p.addPropertyChangeListener(property.toString(), listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see Property
     */
    public void removePropertyChangeListener(Property property,
            PropertyChangeListener listener) {
        p.removePropertyChangeListener(property.toString(), listener);
    }

}
