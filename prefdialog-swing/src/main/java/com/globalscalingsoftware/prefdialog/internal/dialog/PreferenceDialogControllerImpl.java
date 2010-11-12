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
package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.awt.Frame;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PreferenceDialogControllerImpl implements
		PreferenceDialogController, PreferenceDialogControllerInternal {

	private final PreferenceDialog preferenceDialog;
	private final InputFieldsFactory inputFieldsFactory;
	private Options option;
	private PreferencePanels preferencePanels;
	private Object preferences;

	@Inject
	PreferenceDialogControllerImpl(PreferenceDialog preferenceDialog,
			InputFieldsFactory inputFieldsFactory) {
		this.preferenceDialog = preferenceDialog;
		this.inputFieldsFactory = inputFieldsFactory;
		this.option = Options.CANCEL;
	}

	@Override
	public void setup(Frame owner) {
		setupRootNode();
		setupChildSelectedAction();
		setupPreferencesStart();
		preferenceDialog.setup(owner);
	}

	private void setupRootNode() {
		preferencePanels = inputFieldsFactory.createRootNode(preferences);
		preferenceDialog.setRootNode(preferencePanels.getRootNode());
	}

	private void setupChildSelectedAction() {
		preferenceDialog.setChildSelected(new ChildSelectedAction(this));
	}

	private void setupPreferencesStart() {
		FieldHandler<?> preferencesStart = preferencePanels
				.getPreferencesStart();
		JPanel panel = (JPanel) preferencesStart.getAWTComponent();
		preferenceDialog.setChildPanel(panel);
		Object value = preferencesStart.getComponentValue();
		preferenceDialog.setSelectedChild(preferencePanels.getPath(value));
	}

	@Override
	public void openDialog() {
		preferenceDialog.open();
	}

	@Override
	@Inject
	public void setPreferences(@Named("preferences") Object preferences) {
		this.preferences = preferences;
	}

	public JDialog getPreferenceDialog() {
		return preferenceDialog.getUiPreferencesDialog();
	}

	void setChildPanel(Object object) {
		FieldHandler<?> fieldHandler = preferencePanels.getFieldHandler(object);
		JPanel panel = (JPanel) fieldHandler.getAWTComponent();
		preferenceDialog.setChildPanel(panel);
	}

	@Override
	public Options getOption() {
		return option;
	}

	@Override
	public void setOkAction(Action a) {
		preferenceDialog.setOkAction(a);
	}

	@Override
	public void setCancelAction(Action a) {
		preferenceDialog.setCancelAction(a);
	}

	@Override
	public void setApplyAction(Action a) {
		inputFieldsFactory.setApplyAction(a);
	}

	@Override
	public void setRestoreAction(Action a) {
		inputFieldsFactory.setRestoreAction(a);
	}

	@Override
	public void closeDialog(Options option) {
		this.option = option;
		preferenceDialog.close();
	}

	@Override
	public Map<Object, FieldHandler<?>> getPreferencePanels() {
		return preferencePanels.getPreferencePanels();
	}

}
