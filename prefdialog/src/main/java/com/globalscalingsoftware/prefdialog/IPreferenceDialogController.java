package com.globalscalingsoftware.prefdialog;

import javax.swing.Action;
import javax.swing.JFrame;

public interface IPreferenceDialogController {

	void setPreferences(Object preferences);

	void setChildObject(Object object);

	void openDialog();

	void setOwner(JFrame owner);

	void setOkAction(Action action);

	void setCancelAction(Action action);

	void setApplyAction(IApplyAction applyAction);

	void setRestoreAction(IRestoreAction restoreAction);

}