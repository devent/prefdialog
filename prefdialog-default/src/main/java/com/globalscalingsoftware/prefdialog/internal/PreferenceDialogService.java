package com.globalscalingsoftware.prefdialog.internal;

import com.globalscalingsoftware.prefdialog.IExternalModule;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.IPreferenceDialogService;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

public class PreferenceDialogService implements IPreferenceDialogService {

	private final PreferenceDialogServiceModule module;

	private final Module externalModule;

	@Inject
	PreferenceDialogService(IExternalModule externalModule) {
		this.externalModule = (Module) externalModule;
		module = new PreferenceDialogServiceModule();
	}

	@Override
	public IPreferenceDialogController getPreferenceDialog() {
		Injector injector = Guice.createInjector(externalModule, module);
		return injector.getInstance(IPreferenceDialogController.class);
	}

}
