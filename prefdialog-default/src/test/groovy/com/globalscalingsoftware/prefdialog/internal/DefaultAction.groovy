package com.globalscalingsoftware.prefdialog.internal

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.globalscalingsoftware.prefdialog.IApplyAction;
import com.globalscalingsoftware.prefdialog.ICancelAction;
import com.globalscalingsoftware.prefdialog.IOkAction;
import com.globalscalingsoftware.prefdialog.IRestoreAction;

class DefaultAction extends AbstractAction 
implements IOkAction, ICancelAction, IApplyAction, IRestoreAction {
	
	def DefaultAction(String name) {
		super(name)
	}
	
	void actionPerformed(ActionEvent e) {
		assert true
	}
}
