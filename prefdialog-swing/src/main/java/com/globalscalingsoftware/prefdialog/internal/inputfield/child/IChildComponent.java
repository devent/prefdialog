package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldComponent;

public interface IChildComponent extends FieldComponent {

	void addField(FieldHandler<?> inputField);

	void setApplyEvent(Runnable applyEvent);

	void setApplyAction(Action a);

	void setRestoreAction(Action a);

}
