package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ComboBoxHistoryModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				HistoryComboBoxModel.class, HistoryComboBoxModel.class).build(
				HistoryComboBoxModelFactory.class));
		install(new FactoryModuleBuilder().implement(HistoryComboBox.class,
				HistoryComboBox.class).build(HistoryComboBoxFactory.class));
	}

}
