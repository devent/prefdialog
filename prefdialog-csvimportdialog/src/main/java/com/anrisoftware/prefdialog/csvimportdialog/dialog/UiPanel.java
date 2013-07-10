/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

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
