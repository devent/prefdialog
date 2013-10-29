package com.anrisoftware.prefdialog.miscswing.chart.datamodel;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the data chart model factory.
 * 
 * @see DataChartModelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ChartDataModel extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(DataChartModel.class,
				DataChartModel.class).build(DataChartModelFactory.class));
	}

}
