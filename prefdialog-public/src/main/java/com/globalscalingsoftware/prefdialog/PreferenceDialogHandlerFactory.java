package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

import com.google.inject.assistedinject.Assisted;

public interface PreferenceDialogHandlerFactory {

	PreferenceDialogHandler create(@Assisted Frame owner,
			@Assisted Object preferences);
}
