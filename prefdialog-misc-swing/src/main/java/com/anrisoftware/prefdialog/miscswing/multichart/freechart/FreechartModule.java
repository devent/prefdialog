package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import com.anrisoftware.prefdialog.miscswing.multichart.chart.Chart;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the <i>Freechart</i> chart factory.
 * 
 * @see FreechartXYChartFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class FreechartModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(Chart.class,
                FreechartXYChart.class).build(FreechartXYChartFactory.class));
    }

}
