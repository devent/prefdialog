package com.globalscalingsoftware.prefdialog.internal.module;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PreferenceDialogService implements IPreferenceDialogService {

	private final PreferenceDialogModule module;

	public PreferenceDialogService(PreferenceDialogModule module) {
		this.module = module;
	}

	@Override
	public IPreferenceDialogController getPreferenceDialog() {
		Injector injector = Guice.createInjector(module);
		return injector.getInstance(IPreferenceDialogController.class);
	}

}
