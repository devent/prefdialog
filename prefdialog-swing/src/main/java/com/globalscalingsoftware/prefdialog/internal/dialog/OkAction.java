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

import java.awt.event.ActionEvent;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildFieldHandler;
import com.google.inject.Inject;

public class OkAction extends AbstractDelegateAction {

	private final PreferenceDialogControllerInternal controller;

	@Inject
	OkAction(PreferenceDialogControllerInternal controller) {
		super("Ok");
		this.controller = controller;
	}

	@Override
	protected void customActionPerformed(ActionEvent e) {
		applyAllInput();
		controller.closeDialog(Options.OK);
	}

	private void applyAllInput() {
		Map<Object, FieldHandler<?>> panels = controller.getPreferencePanels();
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
}
