package com.anrisoftware.prefdialog.csvimportdialog.panel;

import static java.awt.BorderLayout.CENTER;

import java.awt.Component;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.google.inject.assistedinject.Assisted;

public class CsvImportPanel {

	private final UiPanel panel;

	private final FieldComponent<JPanel> propertiesPanel;

	@Inject
	CsvImportPanel(UiPanel panel, FieldFactory<JPanel> panelFieldFactory,
			@Assisted Object properties) {
		this.panel = panel;
		this.propertiesPanel = panelFieldFactory.create(properties,
				"importPanel");
		setupPanel();
	}

	private void setupPanel() {
		panel.add(propertiesPanel.getAWTComponent(), CENTER);
	}

	public Component getAWTComponent() {
		return panel;
	}
}
