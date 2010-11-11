package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.Options;

public interface PreferenceDialogControllerInternal {

	void closeDialog(Options option);

	Map<Object, FieldHandler<?>> getPreferencePanels();

}