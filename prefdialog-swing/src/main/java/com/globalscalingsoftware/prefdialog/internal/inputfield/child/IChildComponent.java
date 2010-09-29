package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.InputFieldComponent;

public interface IChildComponent extends InputFieldComponent {

	void addField(InputField<?> inputField);

	void setApplyEvent(Runnable applyEvent);

	void setApplyAction(Action a);

	void setRestoreAction(Action a);

}
