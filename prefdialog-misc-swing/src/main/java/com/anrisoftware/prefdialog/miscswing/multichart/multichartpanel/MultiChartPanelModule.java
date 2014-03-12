package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class MultiChartPanelModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(MultiChartPanel.class,
                MultiChartPanel.class).build(MultiChartPanelFactory.class));
    }

}
