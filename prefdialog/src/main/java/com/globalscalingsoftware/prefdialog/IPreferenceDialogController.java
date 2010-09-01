package com.globalscalingsoftware.prefdialog;

import javax.swing.JFrame;

public interface IPreferenceDialogController {

	void setPreferences(Object preferences);

	void setChildObject(Object object);

	void openDialog();

	void setOwner(JFrame owner);

}