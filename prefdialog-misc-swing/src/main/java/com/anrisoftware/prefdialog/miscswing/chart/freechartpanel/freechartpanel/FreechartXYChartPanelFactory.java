package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

/**
 * Factory to create the JFreeChart X/Y line chart panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface FreechartXYChartPanelFactory {

	/**
	 * Creates the JFreeChart X/Y line chart panel.
	 * 
	 * @return the {@link FreechartXYChartPanel}.
	 */
	FreechartXYChartPanel create();
}
