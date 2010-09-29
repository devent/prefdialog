package com.globalscalingsoftware.prefdialog;

public interface IPreferenceDialogController<T extends Enum<?>> {

	void openDialog(Frame owner);

	T getOption();
}