package com.anrisoftware.prefdialog.csvimportdialog.panel;

import static com.anrisoftware.resources.texts.central.TextsResources.ACTIONS_PROPERTY;
import static com.anrisoftware.resources.texts.central.TextsResources.ACTION_MNEMONICS_PROPERTY;
import static com.anrisoftware.resources.texts.central.TextsResources.TEXTS_PROPERTY;

import java.util.Properties;
import java.util.ServiceLoader;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class CsvImportPanelModule extends AbstractModule {

	private static final String VERTICAL_PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(CsvImportPanel.class,
				CsvImportPanel.class).build(CsvImportPanelFactory.class));
	}

	@SuppressWarnings("unchecked")
	@Provides
	@Singleton
	FieldFactory<JPanel> getVerticalPreferencesPanelFieldFactory() {
		for (FieldService service : ServiceLoader.load(FieldService.class)) {
			if (service.getInfo().getAnnotationType().getSimpleName()
					.equals(VERTICAL_PREFERENCES_PANEL_NAME)) {
				return (FieldFactory<JPanel>) service.getFactory();
			}
		}
		addError("Could not find service for %s",
				VERTICAL_PREFERENCES_PANEL_NAME);
		return null;
	}

	@Provides
	@Singleton
	@Named("CsvImportPanel-texts-properties")
	Properties getTextsProperties() {
		Properties p = new Properties();
		p.setProperty(TEXTS_PROPERTY, "CsvImportPanelTexts");
		p.setProperty(ACTIONS_PROPERTY, "CsvImportPanelActions");
		p.setProperty(ACTION_MNEMONICS_PROPERTY,
				"CsvImportPanelActionMnemonics");
		return p;
	}
}
