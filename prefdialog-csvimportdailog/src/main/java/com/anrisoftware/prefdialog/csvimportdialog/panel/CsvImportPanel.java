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

	private final UiPanel panel;

	private final FieldComponent<JPanel> propertiesPanel;

	private final TextsResources texts;

	private final CancelAction cancelAction;

	private final ImportAction importAction;

	private final CsvProperties properties;

	@Inject
	CsvImportPanel(
			TextsResourcesFactory textsFactory,
			@Named("CsvImportPanel-texts-properties") Properties textsProperties,
			UiPanel panel, FieldFactory<JPanel> panelFieldFactory,
			CancelAction cancelAction, ImportAction importAction,
			@Assisted CsvProperties properties) {
		this.panel = panel;
		this.properties = properties;
		this.propertiesPanel = panelFieldFactory.create(properties,
				"importPanel");
		this.texts = textsFactory.create(textsProperties);
		this.cancelAction = cancelAction;
		this.importAction = importAction;
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
		cancelAction.setTexts(texts);
		importAction.setTexts(texts);
		panel.getCancelButton().setAction(cancelAction);
		panel.getImportButton().setAction(importAction);

	}

	private void setupPanel() {
		propertiesPanel.setTexts(texts.getTexts());
		setupMnemomic("locale");
		setupMnemomic("charset");
		setupMnemomic("startRow");
		panel.add(propertiesPanel.getAWTComponent(), CENTER);
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
		return panel;
	}
}
