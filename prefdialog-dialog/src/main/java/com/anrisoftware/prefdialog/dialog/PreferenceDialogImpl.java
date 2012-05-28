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

import static com.anrisoftware.prefdialog.PreferenceDialogStatus.OK;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.PreferenceDialog;
import com.anrisoftware.prefdialog.PreferenceDialogStatus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * The preferences dialog, contains the children panel and additional buttons to
 * apply, ok and cancel the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class PreferenceDialogImpl implements PreferenceDialog {

	private final JDialog dialog;

	private final ChildrenPanel childrenPanel;

	private final JPanel buttonsPanel;

	private final JButton okButton;

	private final JButton cancelButton;

	private final JButton applyButton;

	private final SwingPropertyChangeSupport support;

	private PreferenceDialogStatus status;

	@Inject
	PreferenceDialogImpl(@Assisted JDialog dialog, @Assisted ChildrenPanel panel) {
		this.dialog = dialog;
		this.childrenPanel = panel;
		this.buttonsPanel = new JPanel();
		this.okButton = new JButton();
		this.cancelButton = new JButton();
		this.applyButton = new JButton();
		this.support = new SwingPropertyChangeSupport(this);
		this.status = null;
		setupDialog();
		setupButtonsPanel();
		setupButtons();
	}

	private void setupDialog() {
		dialog.setLayout(new BorderLayout());
		dialog.add(childrenPanel.getPanel(), CENTER);
		dialog.add(buttonsPanel, SOUTH);
		dialog.getRootPane().setDefaultButton(okButton);
	}

	private void setupButtonsPanel() {
		double[] col = { FILL, PREFERRED, PREFERRED, PREFERRED };
		double[] row = { PREFERRED };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(2);
		layout.setVGap(2);

		buttonsPanel.setLayout(layout);

		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		buttonsPanel.add(okButton, "1, 0");
		buttonsPanel.add(applyButton, "2, 0");
		buttonsPanel.add(cancelButton, "3, 0");

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				PreferenceDialogStatus oldValue = status;
				status = OK;
				support.firePropertyChange(PROPERTY_STATUS, oldValue, status);
			}
		});
	}

	private void setupButtons() {
		okButton.setText("Ok");
		cancelButton.setText("Cancel");
		applyButton.setText("Apply");
	}

	@Override
	public void setName(String name) {
		childrenPanel.setName(name);
		dialog.setName(format("%s-%s", name, DIALOG_NAME_POSTFIX));
		okButton.setName(format("%s-%s", name, OK_BUTTON_NAME_POSTFIX));
		cancelButton.setName(format("%s-%s", name, CANCEL_BUTTON_NAME_POSTFIX));
		applyButton.setName(format("%s-%s", name, APPLY_BUTTON_NAME_POSTFIX));
	}

	@Override
	public void setOkAction(Action action) {
		okButton.setAction(action);
	}

	@Override
	public void setCancelAction(Action action) {
		cancelButton.setAction(action);
	}

	@Override
	public void setApplyAction(Action action) {
		applyButton.setAction(action);
	}

	@Override
	public PreferenceDialogStatus getStatus() {
		return status;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
