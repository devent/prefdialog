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

import static com.anrisoftware.prefdialog.dialog.actions.ActionsHandler.APPLY_ACTION_ID;
import static com.anrisoftware.prefdialog.dialog.actions.ActionsHandler.CANCEL_ACTION_ID;
import static com.anrisoftware.prefdialog.dialog.actions.ActionsHandler.OK_ACTION_ID;

import java.awt.Component;
import java.awt.Frame;

import javax.annotation.Nullable;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.collections.MapIterator;

import com.anrisoftware.prefdialog.Options;
import com.anrisoftware.prefdialog.PreferenceDialogHandler;
import com.anrisoftware.prefdialog.PreferencePanelHandler;
import com.anrisoftware.prefdialog.dialog.PreferenceDialog.PreferenceDialogFactory;
import com.anrisoftware.prefdialog.dialog.PreferencePanelsHandler.PreferencePanelsHandlerFactory;
import com.anrisoftware.prefdialog.dialog.actions.ActionsHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferenceDialogHandlerImpl implements PreferenceDialogHandler {

	private final PreferencePanelsHandlerFactory preferencePanelsHandlerFactory;

	private final PreferenceDialogFactory preferenceDialogFactory;

	private final Object preferences;

	private final Frame owner;

	private PreferenceDialog preferenceDialog;

	private Options option;

	private PreferencePanelsHandler preferencePanelsHandler;

	private final ActionsHandler actionsHandler;

	@Inject
	PreferenceDialogHandlerImpl(
			PreferencePanelsHandlerFactory preferencePanelsHandlerFactory,
			PreferenceDialogFactory preferenceDialogFactory,
			ActionsHandler actionsHandler, @Assisted @Nullable Frame owner,
			@Assisted Object preferences) {
		this.preferenceDialogFactory = preferenceDialogFactory;
		this.preferencePanelsHandlerFactory = preferencePanelsHandlerFactory;
		this.actionsHandler = actionsHandler;
		this.owner = owner;
		this.preferences = preferences;
	}

	@Override
	public PreferenceDialogHandler createDialog() {
		setupDialog();
		setupChildSelectedAction();
		setupFirstPreferencesPanelHandler();
		setupActions();
		return this;
	}

	private void setupDialog() {
		preferencePanelsHandler = preferencePanelsHandlerFactory.create(
				preferences).createPanels();
		DefaultMutableTreeNode rootNode = preferencePanelsHandler.getRootNode();
		preferenceDialog = preferenceDialogFactory.create(owner, rootNode)
				.createDialog();
		preferenceDialog.setTitle(preferences.toString());
	}

	private void setupActions() {
		preferenceDialog.setOkAction(actionsHandler.getAction(OK_ACTION_ID));
		preferenceDialog.setCancelAction(actionsHandler
				.getAction(CANCEL_ACTION_ID));
		preferenceDialog.setApplyAction(actionsHandler
				.getAction(APPLY_ACTION_ID));

		actionsHandler.setCallback(OK_ACTION_ID, new Runnable() {

			@Override
			public void run() {
				applyAllInput();
				closeDialog(Options.OK);
			}
		});
		actionsHandler.setCallback(CANCEL_ACTION_ID, new Runnable() {

			@Override
			public void run() {
				restoreAllInput();
				closeDialog(Options.CANCEL);
			}
		});
		actionsHandler.setCallback(APPLY_ACTION_ID, new Runnable() {

			@Override
			public void run() {
				applyAllInput();
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
		this.option = Options.CANCEL;
		preferenceDialog.open();
	}

	@Override
	public Options getOption() {
		return option;
	}

	@Override
	public void setOkAction(Action action) {
		actionsHandler.setDelegate(OK_ACTION_ID, action);
		preferenceDialog.setOkAction(actionsHandler.getAction(OK_ACTION_ID));
		preferenceDialog.setOkAction(actionsHandler.getAction(OK_ACTION_ID));
	}

	@Override
	public void setCancelAction(Action action) {
		actionsHandler.setDelegate(CANCEL_ACTION_ID, action);
		preferenceDialog.setCancelAction(actionsHandler
				.getAction(CANCEL_ACTION_ID));
		preferenceDialog.setCancelAction(actionsHandler
				.getAction(CANCEL_ACTION_ID));
	}

	@Override
	public void setApplyAction(Action action) {
		actionsHandler.setDelegate(APPLY_ACTION_ID, action);
		preferenceDialog.setApplyAction(actionsHandler
				.getAction(APPLY_ACTION_ID));
		preferenceDialog.setApplyAction(actionsHandler
				.getAction(APPLY_ACTION_ID));
	}

	public Component getAWTComponent() {
		return preferenceDialog.getAWTComponent();
	}

	@Override
	public void updateUI() {
		preferenceDialog.updateUI();
		preferencePanelsHandler.updateUI();
	}
}
