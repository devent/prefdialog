package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

import javax.swing.Action;

public interface PreferenceDialogController {

	void setup(Frame owner);

	void openDialog();

	Options getOption();

	void setOkAction(Action a);

}
