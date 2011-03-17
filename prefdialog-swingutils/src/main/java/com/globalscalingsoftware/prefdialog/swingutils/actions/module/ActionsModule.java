package com.globalscalingsoftware.prefdialog.swingutils.actions.module;

import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback;
import com.globalscalingsoftware.prefdialog.swingutils.actions.internal.InputChangedDelegateCallback.InputChangedDelegateCallbackFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryProvider;

public class ActionsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(InputChangedDelegateCallbackFactory.class).toProvider(
				FactoryProvider.newFactory(
						InputChangedDelegateCallbackFactory.class,
						InputChangedDelegateCallback.class));
	}

}
