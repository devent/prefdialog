package com.globalscalingsoftware.prefdialog.panel.inputfields.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.HorizontalPosition;
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

		public void setHorizontalPosition(Field field,
				HorizontalPosition position) {
			log.debug(
					"Set horizontal position to ``{}'' for the field ``{}''.",
					position, field);
		}

	}
}
