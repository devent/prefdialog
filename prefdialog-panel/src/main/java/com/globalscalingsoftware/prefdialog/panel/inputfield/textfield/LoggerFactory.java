package com.globalscalingsoftware.prefdialog.panel.inputfield.textfield;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory
		extends
		com.globalscalingsoftware.prefdialog.panel.inputfield.textfield.shared.SharedTextFieldLoggerFactory {

	@Override
	Logger create(@Assisted Class<?> clazz);

	class Logger
			extends
			com.globalscalingsoftware.prefdialog.panel.inputfield.textfield.shared.SharedTextFieldLoggerFactory.SharedTextFieldLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

	}
}
