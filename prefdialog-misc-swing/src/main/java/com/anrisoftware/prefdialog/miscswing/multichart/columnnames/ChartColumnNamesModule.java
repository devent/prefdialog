package com.anrisoftware.prefdialog.miscswing.multichart.columnnames;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ChartColumnNamesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(DefaultColumnNames.class,
				DefaultColumnNames.class)
				.build(DefaultColumnNamesFactory.class));
	}

}
