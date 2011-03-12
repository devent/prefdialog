/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog;

import javax.swing.Action;

/**
 * <p>
 * The controller for the preference dialog. It is the public API for the
 * preference dialog.
 * </p>
 * 
 * First, the preferences and preferences start objects need to be set. Then,
 * the dialog owner (can be <code>null</code>) need to be set. After that, the
 * dialog can be open and after the dialog is closed the option the user choose
 * (o.k. or cancel) can be returned.
 * 
 * Example code:
 * 
 * <pre>
 * controller.setPreferences(preferences);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * 
 * </pre>
 */
public interface PreferenceDialogController {

	/**
	 * Opens the dialog.
	 */
	void openDialog();

	/**
	 * Returns the {@link Options option} which the user choose to close the
	 * dialog.
	 */
	Options getOption();

	/**
	 * Sets the {@link Action} for the "Ok" button of the dialog.
	 */
	void setOkAction(Action a);

	/**
	 * Sets the {@link Action} for the "Cancel" button of the dialog.
	 */
	void setCancelAction(Action a);

	/**
	 * Sets the {@link Action} for the "Apply" button of the dialog.
	 */
	void setApplyAction(Action a);

	/**
	 * Sets the {@link Action} for the "Restore" button of the dialog.
	 */
	void setRestoreAction(Action a);
}
