package com.anrisoftware.prefdialog.csvimportdialog.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class UiPanel extends JPanel {
	private final JButton importButton;
	private final JButton cancelButton;
	private final JPanel buttonGroupPanel;

	/**
	 * Create the panel.
	 */
	public UiPanel() {
		setLayout(new BorderLayout(0, 0));

		buttonGroupPanel = new JPanel();
		buttonGroupPanel.setName("buttonGroupPanel");
		add(buttonGroupPanel, BorderLayout.SOUTH);
		buttonGroupPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		importButton = new JButton("Import");
		importButton.setName("importButton");
		buttonGroupPanel.add(importButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setName("cancelButton");
		buttonGroupPanel.add(cancelButton);

	}

	public JButton getImportButton() {
		return importButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JPanel getButtonGroupPanel() {
		return buttonGroupPanel;
	}
}
