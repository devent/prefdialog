package com.anrisoftware.prefdialog.csvimportdialog.panel;

import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvProperties;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.assistedinject.Assisted;

public class CsvImportPanel {

	private final JPanel container;

	private final FieldComponent<JPanel> propertiesPanel;

	private final Texts texts;

	private final CsvProperties properties;

	@Inject
	CsvImportPanel(TextsFactory textsFactory,
			FieldFactory<JPanel> panelFieldFactory, @Assisted JPanel container,
			@Assisted CsvProperties properties) {
		this.container = container;
		this.properties = properties;
		this.propertiesPanel = panelFieldFactory.create(properties,
				"importPanel");
		this.texts = textsFactory.create(CsvImportPanel.class.getSimpleName());
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
		propertiesPanel.setTexts(texts);
		container.setLayout(new BorderLayout());
		container.add(propertiesPanel.getAWTComponent(), CENTER);
	}

	public Component getAWTComponent() {
		return container;
	}

	public CsvProperties getProperties() {
		return properties;
	}
}
