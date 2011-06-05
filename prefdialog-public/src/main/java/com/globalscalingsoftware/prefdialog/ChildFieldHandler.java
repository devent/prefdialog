package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

import javax.swing.Action;

public interface ChildFieldHandler {

	void setup();

	void setButtonsTransparent(boolean transparent);

	Component getAWTComponent();

	void setApplyAction(Action a);

	void setRestoreAction(Action a);
}
