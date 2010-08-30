package com.globalscalingsoftware.prefdialog.internal;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PreferenceDialogService implements IPreferenceDialogService {

	private final PreferenceDialogServiceModule module;

	PreferenceDialogService() {
		module = new PreferenceDialogServiceModule();
	}

	@Override
	public IPreferenceDialogController getPreferenceDialog() {
		Injector injector = Guice.createInjector(module);
		return injector.getInstance(IPreferenceDialogController.class);
	}

}
