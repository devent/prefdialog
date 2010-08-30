package com.globalscalingsoftware.prefdialog;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;

public interface IUiPreferencesDialog {

	JPanel getDialogPanel();

	JSplitPane getSplitPane();

	JTree getChildTree();

	JButton getCancelButton();

	JButton getOkButton();

}