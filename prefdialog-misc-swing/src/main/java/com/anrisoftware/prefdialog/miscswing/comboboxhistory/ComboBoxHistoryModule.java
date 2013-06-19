package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static com.google.inject.Guice.createInjector;

import javax.swing.ListCellRenderer;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ComboBoxHistoryModule extends AbstractModule {

	private static Injector injector;

	public static Injector getInjector() {
		if (injector == null) {
			synchronized (ComboBoxHistoryModule.class) {
				injector = createInjector(new ComboBoxHistoryModule());
			}
		}
		return injector;
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				HistoryComboBoxModel.class, HistoryComboBoxModel.class).build(
				HistoryComboBoxModelFactory.class));
		install(new FactoryModuleBuilder().implement(HistoryComboBox.class,
				HistoryComboBox.class).build(HistoryComboBoxFactory.class));
		install(new FactoryModuleBuilder().implement(ItemDefault.class,
				ItemDefault.class).build(ItemDefaultFactory.class));
		install(new FactoryModuleBuilder().implement(ListCellRenderer.class,
				ItemsDefaultComboBoxRenderer.class).build(
				ItemsDefaultComboBoxRendererFactory.class));
	}

}
