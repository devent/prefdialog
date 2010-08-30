package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

import javax.swing.Action;
import javax.swing.JPanel;

public interface IPreferencePanel {

	JPanel getPanel();

	void setTitle(String title);

	void addFormattedTextField(Object parentObject, Field field, Object value);

	void addTextField(final Object parentObject, final Field field, Object value);

	void setApplyAction(Action a);

	void setApplyEvent(Runnable applyEvent);

	void setRestoreAction(Action a);

	void applyAllInput();

	void undoAllInput();

}