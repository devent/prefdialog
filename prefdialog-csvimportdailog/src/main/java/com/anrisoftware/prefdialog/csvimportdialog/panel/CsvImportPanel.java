package com.anrisoftware.prefdialog.csvimportdialog.panel;

import static java.awt.BorderLayout.CENTER;

import java.awt.Component;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvProperties;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.resources.texts.central.TextsResources;
import com.anrisoftware.resources.texts.central.TextsResourcesFactory;
import com.google.inject.assistedinject.Assisted;

public class CsvImportPanel {

	private final JPanel container;

	private final FieldComponent<JPanel> propertiesPanel;

	private final TextsResources texts;

	private final CsvProperties properties;

	@Inject
	CsvImportPanel(
			TextsResourcesFactory textsFactory,
			@Named("CsvImportPanel-texts-properties") Properties textsProperties,
			FieldFactory<JPanel> panelFieldFactory, @Assisted JPanel container,
			@Assisted CsvProperties properties) {
		this.container = container;
		this.properties = properties;
		this.propertiesPanel = panelFieldFactory.create(properties,
				"importPanel");
		this.texts = textsFactory.create(textsProperties);
		setupPanel();
		setupActions();
	}

	private void setupActions() {
		properties
				.getSeparatorProperties()
				.getUseCustomSeparatorAction()
				.setCustomSeparatorCharField(
						propertiesPanel.findField("customSeparatorChar"));
		properties
				.getSeparatorProperties()
				.getUseCustomSeparatorAction()
				.setSeparatorCharField(
						propertiesPanel.findField("separatorChar"));
	}

	private void setupPanel() {
		propertiesPanel.setTexts(texts.getTexts());
		setupMnemomic("locale");
		setupMnemomic("charset");
		setupMnemomic("startRow");
		container.add(propertiesPanel.getAWTComponent(), CENTER);
	}

	private void setupMnemomic(String name) {
		AbstractTitleField<?> field;
		field = (AbstractTitleField<?>) propertiesPanel.findField(name);
		if (field == null) {
			return;
		}
		field.getTitleLabel().setDisplayedMnemonic(texts.getMnemonic(name));
		int index = texts.getMnemonicIndex(name);
		if (index != -1) {
			field.getTitleLabel().setDisplayedMnemonicIndex(index);
		}
	}

	public Component getAWTComponent() {
		return container;
	}

	public CsvProperties getProperties() {
		return properties;
	}
}
