package com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox;

import com.globalscalingsoftware.prefdialog.swingutils.LoggerFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory extends LoggerFactory {

	@Override
	Logger create(@Assisted Class<?> clazz);

	class Logger extends Logger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setText(String text) {
			log.debug("Set check box text to ``{}''.", text);
		}

	}
}
