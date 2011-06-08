package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory
		extends
		com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.shared.LoggerFactory {

	@Override
	Logger create(@Assisted Class<?> clazz);

	class Logger
			extends
			com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.shared.LoggerFactory.Logger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

	}
}
