package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

public interface PreferenceDialogController {

	void openDialog(Frame owner);

	Options getOption();
}
