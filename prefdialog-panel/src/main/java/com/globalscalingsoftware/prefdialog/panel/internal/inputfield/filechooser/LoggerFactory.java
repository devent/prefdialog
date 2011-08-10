package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.log.AbstractLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void openFileChooserDialog(Field field) {
			log.debug("Open file chooser dialog for the field ``{}''.", field);
		}

	}
}
