package com.globalscalingsoftware.prefdialog.internal

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.globalscalingsoftware.prefdialog.annotations.actions.ApplyAction;
import com.globalscalingsoftware.prefdialog.annotations.actions.RestoreAction;

@ApplyAction
@RestoreAction
class DefaultAction extends AbstractAction {
	
	def DefaultAction(String name) {
		super(name)
	}
	
	void actionPerformed(ActionEvent e) {
		assert true
	}
}
