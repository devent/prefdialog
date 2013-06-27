package com.anrisoftware.prefdialog.csvimportdialog.importpaneldock;

import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.google.inject.Injector;

public class ImportPanelDock implements EditorDockWindow {

	public static final String ID = "importPanelDock";

	private final JPanel panel;

	private transient CsvImportPanelFactory panelFactory;

	@Inject
	ImportPanelDock(CsvImportPanelFactory panelFactory) {
		this.panelFactory = panelFactory;
		this.panel = new JPanel();
	}

	/**
	 * Sets the CSV import properties.
	 * 
	 * @param injector
	 *            the parent {@link Injector}.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 */
	public void createPanel(Injector injector, CsvImportProperties properties) {
		CsvImportPanel importPanel = panelFactory.create(panel, properties);
		importPanel.createPanel(injector);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getTitle() {
		return "Import Panel";
	}

	@Override
	public Component getComponent() {
		return panel;
	}

	@Override
	public DockPosition getPosition() {
		return DockPosition.EAST;
	}

	@Override
	public boolean isCloseable() {
		return false;
	}

	@Override
	public boolean isExternalizable() {
		return false;
	}

	@Override
	public boolean isMaximizable() {
		return false;
	}

	@Override
	public boolean isMinimizable() {
		return false;
	}

	@Override
	public boolean isStackable() {
		return false;
	}

}
