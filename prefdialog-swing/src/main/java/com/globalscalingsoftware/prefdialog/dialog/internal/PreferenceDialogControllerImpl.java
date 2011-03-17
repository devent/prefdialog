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
package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JPanel;

import org.apache.commons.collections.MapIterator;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferenceDialog.PreferenceDialogFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.actions.ActionsHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

public class PreferenceDialogControllerImpl implements
		PreferenceDialogController {

	private final PreferenceDialog preferenceDialog;

	private final PreferencePanelsHandler preferencePanelsHandler;

	private final ActionsHandler actionsHandler;

	private Options option;

	private final Object preferences;

	@Inject
	PreferenceDialogControllerImpl(ActionsHandler actionsHandler,
			PreferencePanelsHandler preferencePanelsHandler,
			PreferenceDialogFactory preferenceDialogFactory,
			@Assisted @Nullable Frame owner, @Assisted Object preferences) {
		this.actionsHandler = actionsHandler;
		this.preferencePanelsHandler = preferencePanelsHandler;
		this.preferenceDialog = preferenceDialogFactory.create(owner,
				preferencePanelsHandler.getRootNode());
		this.preferences = preferences;
		this.option = Options.CANCEL;
		setup();
	}

	private void setup() {
		setupDialog();
		setupChildSelectedAction();
		setupFirstPreferencesPanelHandler();
		setupActions();
		setupInputChangedActions();
	}

	private void setupInputChangedActions() {
		preferencePanelsHandler
				.setInputChangedCallback(new InputChangedCallback() {

					@Override
					public void inputChanged(Object source) {
						boolean inputValid = preferencePanelsHandler
								.isInputValid();
						actionsHandler.setOkActionEnabled(inputValid);
					}
				});
	}

	private void setupDialog() {
		preferenceDialog.setTitle(preferences.toString());
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
		MapIterator panels = preferencePanelsHandler.getPreferencePanels();
		while (panels.hasNext()) {
			panels.next();
			PreferencePanelHandler handler = (PreferencePanelHandler) panels
					.getValue();
			handler.restoreInput();
		}
	}

	private void applyAllInput() {
		MapIterator panels = preferencePanelsHandler.getPreferencePanels();
		while (panels.hasNext()) {
			panels.next();
			PreferencePanelHandler handler = (PreferencePanelHandler) panels
					.getValue();
			handler.applyInput();
		}
	}

	private void closeDialog(Options option) {
		this.option = option;
		preferenceDialog.close();
	}

	private void setupChildSelectedAction() {
		preferenceDialog.setChildSelected(new ChildSelectedCallback() {

			@Override
			public void call(Object value) {
				PreferencePanelHandler handler = preferencePanelsHandler
						.getPreferencePanelHandler(value);
				JPanel panel = (JPanel) handler.getAWTComponent();
				preferenceDialog.setChildPanel(panel);
			}
		});
	}

	private void setupFirstPreferencesPanelHandler() {
		PreferencePanelHandler firstHandler = preferencePanelsHandler
				.getFirstPreferencePanelHandler();
		JPanel panel = (JPanel) firstHandler.getAWTComponent();
		preferenceDialog.setChildPanel(panel);
		Object value = firstHandler.getPreferences();
		preferenceDialog.setSelectedChild(preferencePanelsHandler
				.getPath(value));
	}

	@Override
	public void openDialog() {
		preferenceDialog.open();
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
		MapIterator panels = preferencePanelsHandler.getPreferencePanels();
		while (panels.hasNext()) {
			panels.next();
			PreferencePanelHandler handler = (PreferencePanelHandler) panels
					.getValue();
			handler.setApplyAction(action);
		}
	}

	@Override
	public void setRestoreAction(Action action) {
		MapIterator panels = preferencePanelsHandler.getPreferencePanels();
		while (panels.hasNext()) {
			panels.next();
			PreferencePanelHandler handler = (PreferencePanelHandler) panels
					.getValue();
			handler.setRestoreAction(action);
		}
	}

	public Component getPreferenceDialog() {
		return preferenceDialog.getAWTComponent();
	}
}
