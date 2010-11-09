package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

public interface PreferenceDialogController {

	void setup(Frame owner);

	void openDialog();

	Options getOption();

}
