package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.BorderLayout;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.panel.CsvImportPanelFactory;
import com.anrisoftware.resources.texts.central.TextsResourcesFactory;
import com.google.inject.assistedinject.Assisted;

public class CsvImportDialog {

	private final CancelAction cancelAction;

	private final ImportAction importAction;

	private final CsvProperties properties;

	private final CsvImportPanel panel;

	private final UiDialog dialog;

	private final JPanel container;

	@Inject
	CsvImportDialog(
			UiDialog dialog,
			TextsResourcesFactory textsFactory,
			@Named("CsvImportPanel-texts-properties") Properties textsProperties,
			CsvImportPanelFactory panelFactory, CancelAction cancelAction,
			ImportAction importAction, @Assisted CsvProperties properties) {
		this.dialog = dialog;
		this.container = new JPanel();
		this.panel = panelFactory.create(container, properties);
		this.properties = properties;
		this.cancelAction = cancelAction;
		this.importAction = importAction;
		setupDialog();
		setupActions();
	}

	private void setupDialog() {
		dialog.add(container, BorderLayout.CENTER);
	}

	private void setupActions() {
		dialog.getCancelButton().setAction(cancelAction);
		dialog.getImportButton().setAction(importAction);
	}

	public CsvProperties getProperties() {
		return properties;
	}

	public void openDialog() {
		dialog.setVisible(true);
	}

	public void closeDialog() {
		dialog.setVisible(false);
	}
}
