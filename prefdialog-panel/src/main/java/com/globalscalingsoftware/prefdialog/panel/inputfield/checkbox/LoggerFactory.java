package com.globalscalingsoftware.prefdialog.panel.inputfield.checkbox;

import com.globalscalingsoftware.prefdialog.panel.inputfield.shared.SharedSwingLoggerFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory extends SharedSwingLoggerFactory {

	@Override
	Logger create(@Assisted Class<?> clazz);

	class Logger extends SharedSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setText(String text) {
			log.debug("Set check box text to ``{}''.", text);
		}

	}
}
