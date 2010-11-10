package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.FieldHandler;

public interface IChildComponent extends FieldComponent {

	void addField(FieldHandler<?> inputField);

	void setApplyEvent(Runnable e);

	void setApplyAction(Action a);

	void setRestoreAction(Action a);

	void setRestoreEvent(Runnable e);

}
