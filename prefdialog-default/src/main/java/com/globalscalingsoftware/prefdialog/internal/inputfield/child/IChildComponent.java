package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.IComponent;

public interface IChildComponent extends IComponent {

	void addField(IInputField inputField);

	void setApplyEvent(Runnable applyEvent);

	void setApplyAction(Action a);

	void setRestoreAction(Action a);
}
