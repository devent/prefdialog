package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the {@code JFreeChart} X/Y line chart panel factory.
 * 
 * @see FreechartXYChartPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class FreechartPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				FreechartXYChartPanel.class, FreechartXYChartPanel.class)
				.build(FreechartXYChartPanelFactory.class));
		install(new FactoryModuleBuilder().implement(GraphScrollModel.class,
				GraphScrollModel.class).build(GraphScrollModelFactory.class));
	}

}
