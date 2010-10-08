package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildFieldHandler;

class OkEvent implements Runnable {

	private final PreferenceDialogControllerImpl controller;

	public OkEvent(PreferenceDialogControllerImpl controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		Map<Object, FieldHandler<?>> panels = controller.getPreferencePanels();
		for (FieldHandler<?> field : panels.values()) {
			if (field instanceof AbstractChildFieldHandler) {
				AbstractChildFieldHandler<?> child = (AbstractChildFieldHandler<?>) field;
				child.applyInput();
			}
		}

		controller.closeDialog(Options.OK);
	}

}
