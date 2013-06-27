package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.panel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.panel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.CsvProperties;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class CsvImportDialog {

	private final CancelAction cancelAction;

	private final ImportAction importAction;

	private final CsvProperties properties;

	private final CsvImportPanel panel;

	private final UiPanel dialogPanel;

	private final JPanel container;

	private final JPanel panelContainer;

	private final Texts texts;

	@Inject
	CsvImportDialog(UiPanel dialog, CsvImportPanelFactory panelFactory,
			TextsFactory textsFactory, CancelAction cancelAction,
			ImportAction importAction, @Assisted JPanel container,
			@Assisted CsvProperties properties) {
		this.texts = textsFactory.create(CsvImportDialog.class.getSimpleName());
		this.container = container;
		this.dialogPanel = dialog;
		this.panelContainer = new JPanel();
		this.panel = panelFactory.create(panelContainer, properties);
		this.properties = properties;
		this.cancelAction = cancelAction;
		this.importAction = importAction;
		setupDialog();
		setupActions();
	}

	private void setupDialog() {
		container.removeAll();
		container.setLayout(new BorderLayout());
		container.add(dialogPanel, BorderLayout.CENTER);
		dialogPanel.add(panelContainer, BorderLayout.CENTER);
	}

	private void setupActions() {
		importAction.setTexts(texts);
		cancelAction.setTexts(texts);
		dialogPanel.getCancelButton().setAction(cancelAction);
		dialogPanel.getImportButton().setAction(importAction);
	}

	public void createDialog(Injector injector) {
		panel.createPanel(injector);
	}

	public CsvProperties getProperties() {
		return properties;
	}

	public void openDialog() {
		dialogPanel.setVisible(true);
	}

	public void closeDialog() {
		dialogPanel.setVisible(false);
	}

	public Component getAWTComponent() {
		return dialogPanel;
	}
}
