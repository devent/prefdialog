package com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the data bean chart model.
 * 
 * @see DataBeanChartModelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DataBeanModelModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(ChartModel.class,
                DataBeanChartModel.class)
                .build(DataBeanChartModelFactory.class));
    }

}
