package com.globalscalingsoftware.prefdialog;

import javax.swing.Action;
import javax.swing.JFrame;

public interface IPreferenceDialogController {

	void setPreferences(Object preferences);

	void setChildObject(Object object);

	void openDialog();

	void setOwner(JFrame owner);

	void setOkAction(Action okAction);

	void setCancelAction(Action cancelAction);

	void setApplyAction(Action applyAction);

	void setRestoreAction(Action restoreAction);

}