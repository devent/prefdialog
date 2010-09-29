package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

public interface IPreferenceDialogController {

	void openDialog(Frame owner);

	Options getOption();
}