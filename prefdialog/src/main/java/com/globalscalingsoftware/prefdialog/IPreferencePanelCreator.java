package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

import javax.swing.Action;

public interface IPreferencePanelCreator {

	IPreferencePanel createPanel(Object parentValue, Field field);

	void setApplyAction(Action applyAction);

	void setRestoreAction(Action restoreAction);

}