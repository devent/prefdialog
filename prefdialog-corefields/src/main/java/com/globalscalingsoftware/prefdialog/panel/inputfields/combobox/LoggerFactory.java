package com.globalscalingsoftware.prefdialog.panel.inputfields.combobox;

import java.util.Collection;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new combobox field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new combobox field {@link Logger} for the given {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the combobox field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setRenderer(Object renderer, Object handler) {
			log.debug("Set new list cell renderer {} for the handler {}.",
					renderer, handler);
		}

		void setModel(Object model, Object handler) {
			log.debug("Set new combo box model {} for the handler {}.", model,
					handler);
		}

		void setValues(Collection<?> values, Object handler) {
			log.debug("Set new values ``{}'' for the handler {}.", values,
					handler);
		}

	}
}
