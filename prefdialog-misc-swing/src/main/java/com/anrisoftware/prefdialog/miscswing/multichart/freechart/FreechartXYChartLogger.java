/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.miscswing.multichart.freechart.FreechartXYChartLogger._.chart_changed;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModelEvent;

/**
 * Logging for {@link FreechartXYChart}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FreechartXYChartLogger extends AbstractLogger {

    enum _ {

        chart_changed("Chart {} changed {}.");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link FreechartXYChart}.
     */
    public FreechartXYChartLogger() {
        super(FreechartXYChart.class);
    }

    void chartChanged(FreechartXYChart chart, ChartModelEvent e) {
        debug(chart_changed, chart, e);
    }
}
