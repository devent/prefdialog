package com.anrisoftware.prefdialog.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class LookAndFeelsModel extends DefaultComboBoxModel {

	public LookAndFeelsModel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			addElement(new LookAndFeelInfoListItem(info));
		}
	}
}
