package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.IPreferenceDialogOwner;

import javax.swing.JFrame;

class PreferenceDialogOwner extends JFrame implements IPreferenceDialogOwner {
	
	public PreferenceDialogOwner() {
		setDefaultCloseOperation JFrame.EXIT_ON_CLOSE
	}
}
