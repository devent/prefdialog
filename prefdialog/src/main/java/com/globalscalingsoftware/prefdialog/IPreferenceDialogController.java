package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

public interface IPreferenceDialogController<T extends Enum<?>> {

	void openDialog(Frame owner);

	T getOption();
}