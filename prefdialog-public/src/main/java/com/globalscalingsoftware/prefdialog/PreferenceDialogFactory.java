package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

import com.google.inject.assistedinject.Assisted;

public interface PreferenceDialogFactory {

	PreferenceDialogController create(@Assisted Frame owner,
			@Assisted Object preferences);
}
