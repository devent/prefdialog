package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JPanel;

public interface IPreferencePanel {

	JPanel getPanel();

	void setTitle(String title);

	void setApplyAction(Action a);

	void setApplyEvent(Runnable applyEvent);

	void setRestoreAction(Action a);

	void addField(Component field);

}