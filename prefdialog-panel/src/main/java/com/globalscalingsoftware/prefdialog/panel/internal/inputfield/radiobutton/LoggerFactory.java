package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.internal.log.AbstractLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setColumnsRows(Field field, int columns, int rows) {
			log.debug(
					"Set the columns {} and the rows {} for the field ``{}''.",
					new Object[] { columns, rows, field });
		}

		public void addEnumFields(Field field, Enum<?>[] enumFields) {
			log.debug("Set the enum fields {} for the field ``{}''.",
					enumFields, field);
		}

	}
}
