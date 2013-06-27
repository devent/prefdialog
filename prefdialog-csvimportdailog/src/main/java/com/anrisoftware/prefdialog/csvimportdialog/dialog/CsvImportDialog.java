package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.csvimportdialog.importpaneldock.ImportPanelDock;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class CsvImportDialog {

	private final CancelAction cancelAction;

	private final ImportAction importAction;

	private final CsvImportProperties properties;

	private final CsvImportPanel panel;

	private final UiPanel dialogPanel;

	private final Dock dock;

	private final Texts texts;

	private final ImportPanelDock importPanelDock;

	@Inject
	CsvImportDialog(UiPanel dialogPanel, CsvImportPanelFactory panelFactory,
			TextsFactory textsFactory, CancelAction cancelAction,
			ImportAction importAction, DockFactory dockFactory,
			ImportPanelDock importPanelDock, @Assisted JFrame frame,
			@Assisted CsvImportProperties properties) {
		this.texts = textsFactory.create(CsvImportDialog.class.getSimpleName());
		this.dock = dockFactory.create(frame);
		this.importPanelDock = importPanelDock;
		this.dialogPanel = dialogPanel;
		this.panel = panelFactory.create(new JPanel(), properties);
		this.properties = properties;
		this.cancelAction = cancelAction;
		this.importAction = importAction;
		setupDock();
		setupActions();
	}

	private void setupDock() {
		dialogPanel.add(dock.getAWTComponent(), BorderLayout.CENTER);
		dock.addEditorDock(importPanelDock);
	}

	private void setupActions() {
		importAction.setTexts(texts);
		cancelAction.setTexts(texts);
		dialogPanel.getCancelButton().setAction(cancelAction);
		dialogPanel.getImportButton().setAction(importAction);
	}

	public void createDialog(Injector injector) {
		panel.createPanel(injector);
		importPanelDock.createPanel(injector, properties);
	}

	public Dock getDock() {
		return dock;
	}

	public CsvImportProperties getProperties() {
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
