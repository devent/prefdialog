package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.FreechartXYChartPanelLogger._.chart_updated;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent;

/**
 * Logging messages for {@link FreechartXYChartPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class FreechartXYChartPanelLogger extends AbstractLogger {

	enum _ {

		chart_updated("Chart model updated {} for {}.");

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
	 * Creates a logger for {@link FreechartXYChartPanel}.
	 */
	public FreechartXYChartPanelLogger() {
		super(FreechartXYChartPanel.class);
	}

	void chartChanged(FreechartXYChartPanel panel, ChartModelEvent e) {
		debug(chart_updated, e, panel);
	}

}
