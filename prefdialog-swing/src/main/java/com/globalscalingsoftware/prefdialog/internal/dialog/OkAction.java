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