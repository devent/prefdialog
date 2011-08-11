package com.globalscalingsoftware.prefdialog.panel.inputfields.filechooser;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.SharedSwingLoggerFactory;
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

		public void openFileChooserDialog(Field field) {
			log.debug("Open file chooser dialog for the field ``{}''.", field);
		}

	}
}
