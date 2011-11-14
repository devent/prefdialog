package com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new radio button field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new radio button field {@link Logger} for the given
	 * {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the radio button field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setColumns(int columns, Object handler) {
			log.debug("Set the columns {} for the handler {}.", columns,
					handler);
		}

		void setRows(int rows, Object handler) {
			log.debug("Set the rows {} for the handler {}.", rows, handler);
		}

		void addEnumFields(Enum<?>[] enumFields, Object handler) {
			log.debug("Set the enum fields {} for the handler {}.", enumFields,
					handler);
		}

	}
}
