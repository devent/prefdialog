package com.globalscalingsoftware.prefdialog.panel.module;

import com.globalscalingsoftware.prefdialog.panel.internal.InternalModule;
import com.google.inject.AbstractModule;

public class PanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new InternalModule());
	}

}
