package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildInputField;

class CancelEvent implements Runnable {

	private final PreferenceDialogController controller;

	public CancelEvent(PreferenceDialogController controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		Map<Object, FieldHandler<?>> panels = controller.getPreferencePanels();
		for (FieldHandler<?> field : panels.values()) {
			if (field instanceof AbstractChildInputField) {
				AbstractChildInputField<?> child = (AbstractChildInputField<?>) field;
				child.restoreInput();
			}
		}
		controller.closeDialog(Options.CANCEL);
	}

}
