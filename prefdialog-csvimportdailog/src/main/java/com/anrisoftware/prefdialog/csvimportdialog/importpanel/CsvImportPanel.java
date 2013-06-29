package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.UseCustomQuoteAction;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties.UseCustomSeparatorAction;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class CsvImportPanel {

	private final JPanel container;

	private final Texts texts;

	private final CsvPanelProperties properties;

	private final FieldService fieldService;

	private VerticalPreferencesPanelField propertiesPanel;

	@Inject
	CsvImportPanel(TextsFactory textsFactory, FieldService fieldService,
			CsvPanelPropertiesFactory propertiesFactory,
			@Assisted JPanel container, @Assisted CsvImportProperties properties) {
		this.fieldService = fieldService;
		this.container = container;
		this.properties = propertiesFactory.create(properties);
		this.texts = textsFactory.create(CsvImportPanel.class.getSimpleName());
	}

	public void createPanel(Injector injector) {
		this.propertiesPanel = (VerticalPreferencesPanelField) fieldService
				.getFactory(injector).create(properties, "importPanel");
		propertiesPanel.createPanel(injector);
		setupPanel();
		setupActions();
	}

	private void setupActions() {
		UseCustomSeparatorAction useCustomSeparatorAction = properties
				.getSeparatorProperties().getUseCustomSeparatorAction();
		useCustomSeparatorAction.setCustomSeparatorCharField(propertiesPanel
				.findField("customSeparatorChar"));
		useCustomSeparatorAction.setSeparatorCharField(propertiesPanel
				.findField("separatorChar"));
		UseCustomQuoteAction useCustomQuoteAction = properties
				.getAdvancedProperties().getUseCustomQuoteAction();
		useCustomQuoteAction.setQuoteField(propertiesPanel
				.findField("quoteChar"));
		useCustomQuoteAction.setCustomQuoteField(propertiesPanel
				.findField("customQuoteChar"));
	}

	private void setupPanel() {
		propertiesPanel.setTexts(texts);
		container.setLayout(new BorderLayout());
		container.add(propertiesPanel.getAWTComponent(), CENTER);
	}

	public Component getAWTComponent() {
		return container;
	}

	public CsvPanelProperties getProperties() {
		return properties;
	}
}
