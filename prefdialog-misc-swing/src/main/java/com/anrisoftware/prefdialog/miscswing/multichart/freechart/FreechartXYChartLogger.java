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
