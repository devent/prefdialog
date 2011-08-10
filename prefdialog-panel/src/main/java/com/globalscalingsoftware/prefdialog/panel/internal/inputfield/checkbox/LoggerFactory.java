package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setText(String text) {
			log.debug("Set check box text to ``{}''.", text);
		}

	}
}
