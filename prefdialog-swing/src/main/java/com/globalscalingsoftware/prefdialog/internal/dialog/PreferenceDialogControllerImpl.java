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
import com.globalscalingsoftware.prefdialog.internal.dialog.actions.ActionsHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

public class PreferenceDialogControllerImpl implements
		PreferenceDialogController {

	private final PreferenceDialog preferenceDialog;
	private final PreferencePanelsHandler preferencePanelsFactory;
	private Options option;
	private PreferencePanelsCollection preferencePanels;
	private final Object preferences;
	private final ActionsHandler actionsHandler;
	private final Frame owner;

	@Inject
	PreferenceDialogControllerImpl(PreferenceDialog preferenceDialog,
			ActionsHandler actionsHandler,
			PreferencePanelsHandler preferencePanelsFactory,
			@Assisted @Nullable Frame owner, @Assisted Object preferences) {
		this.preferenceDialog = preferenceDialog;
		this.actionsHandler = actionsHandler;
		this.preferencePanelsFactory = preferencePanelsFactory;
		this.owner = owner;
		this.preferences = preferences;
		this.option = Options.CANCEL;
		setup();
	}

	private void setup() {
		setupRootNode();
		setupChildSelectedAction();
		setupPreferencesStart();
		setupActions();
	}

	private void setupRootNode() {
		preferencePanels = preferencePanelsFactory.createPreferencePanelsCollection(preferences);
		preferenceDialog.setup(owner, preferencePanels.getRootNode());
	}

	private void setupActions() {
		Action okAction = actionsHandler.getOkAction();
		preferenceDialog.setOkAction(okAction);
		Action cancelAction = actionsHandler.getCancelAction();
		preferenceDialog.setCancelAction(cancelAction);

		actionsHandler.setOkCallback(new Runnable() {

			@Override
			public void run() {
				applyAllInput();
				closeDialog(Options.OK);
			}
		});
		actionsHandler.setCancelCallback(new Runnable() {

			@Override
			public void run() {
				restoreAllInput();
				closeDialog(Options.CANCEL);
			}
		});
	}

	private void restoreAllInput() {
		Map<Object, ChildFieldHandler> panels = preferencePanels
				.getPreferencePanels();
		for (FieldHandler<?> field : panels.values()) {
			restoreInputForChildField(field);
		}
	}

	private void restoreInputForChildField(FieldHandler<?> field) {
		if (field instanceof AbstractChildFieldHandler) {
			AbstractChildFieldHandler<?> child = (AbstractChildFieldHandler<?>) field;
			child.restoreInput();
		}
	}

	private void applyAllInput() {
		Map<Object, ChildFieldHandler> panels = preferencePanels
				.getPreferencePanels();
		for (FieldHandler<?> field : panels.values()) {
			applyInputForChildField(field);
		}
	}

	private void applyInputForChildField(FieldHandler<?> field) {
		if (field instanceof AbstractChildFieldHandler) {
			AbstractChildFieldHandler<?> child = (AbstractChildFieldHandler<?>) field;
			child.applyInput();
		}
	}

	private void closeDialog(Options option) {
		this.option = option;
		preferenceDialog.close();
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

	JDialog getPreferenceDialog() {
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
	public void setOkAction(Action action) {
		actionsHandler.setOkParentAction(action);
	}

	@Override
	public void setCancelAction(Action action) {
		actionsHandler.setCancelParentAction(action);
	}

	@Override
	public void setApplyAction(Action action) {
		actionsHandler.setApplyParentAction(action);
	}

	@Override
	public void setRestoreAction(Action action) {
		actionsHandler.setRestoreParentAction(action);
	}

}
