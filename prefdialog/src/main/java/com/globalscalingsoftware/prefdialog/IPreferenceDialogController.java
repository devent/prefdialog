package com.globalscalingsoftware.prefdialog;

import javax.swing.JFrame;

public interface IPreferenceDialogController {

	void openDialog();

	void setOwner(JFrame owner);

}