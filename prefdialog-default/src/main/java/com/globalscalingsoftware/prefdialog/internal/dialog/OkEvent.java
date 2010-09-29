package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Map;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.AbstractChildInputField;

class OkEvent implements Runnable {

	private final PreferenceDialogController controller;

	public OkEvent(PreferenceDialogController controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		Map<Object, InputField<?>> panels = controller.getPreferencePanels();
		for (InputField<?> field : panels.values()) {
			if (field instanceof AbstractChildInputField) {
				AbstractChildInputField<?> child = (AbstractChildInputField<?>) field;
				child.applyInput();
			}
		}

		controller.closeDialog();
	}

}
