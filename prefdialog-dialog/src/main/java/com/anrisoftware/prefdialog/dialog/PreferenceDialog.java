/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog;

import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * The preferences dialog, contains the children panel and additional buttons to
 * apply, ok and cancel the dialog.
 */
class PreferenceDialog {

	private final JDialog dialog;

	private final ChildrenPanel childrenPanel;

	private final JPanel buttonsPanel;

	private final JButton okButton;

	private final JButton cancelButton;

	private final JButton applyButton;

	@Inject
	PreferenceDialog(@Assisted JDialog dialog,
			@Assisted ChildrenPanel childrenPanel) {
		this.dialog = dialog;
		this.childrenPanel = childrenPanel;
		this.buttonsPanel = new JPanel();
		this.okButton = new JButton();
		this.cancelButton = new JButton();
		this.applyButton = new JButton();
		setupDialog();
		setupButtonsPanel();
		setupButtons();
	}

	private void setupDialog() {
		dialog.setLayout(new BorderLayout());
		dialog.add(childrenPanel.getPanel(), CENTER);
		dialog.add(buttonsPanel, SOUTH);
	}

	private void setupButtonsPanel() {
		double[] col = { FILL, PREFERRED, PREFERRED, PREFERRED };
		double[] row = { PREFERRED };
		buttonsPanel.setLayout(new TableLayout(col, row));
		buttonsPanel.add(okButton, "1, 0");
		buttonsPanel.add(applyButton, "2, 0");
		buttonsPanel.add(cancelButton, "3, 0");
	}

	private void setupButtons() {
		okButton.setText("Ok");
		cancelButton.setText("Cancel");
		applyButton.setText("Apply");
	}

	public void setName(String name) {
		childrenPanel.setName(name);
		dialog.setName(format("%s-%s", name, "preferences-dialog"));
	}

	public void setOkAction(Action action) {
		okButton.setAction(action);
	}

	public void setCancelAction(Action action) {
		cancelButton.setAction(action);
	}

	public void setApplyAction(Action action) {
		applyButton.setAction(action);
	}

}
