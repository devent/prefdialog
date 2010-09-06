package com.globalscalingsoftware.prefdialog;

import javax.swing.JPanel;

public interface IPreferencePanelController {

	JPanel getPanel();

	void applyAllInput();

	void undoAllInput();

}
