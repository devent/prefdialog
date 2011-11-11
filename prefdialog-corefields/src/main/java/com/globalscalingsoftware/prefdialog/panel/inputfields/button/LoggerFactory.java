package com.globalscalingsoftware.prefdialog.panel.inputfields.button;

import com.globalscalingsoftware.prefdialog.annotations.HorizontalPosition;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new button field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new button field {@link Logger} for the given {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the button field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setHorizontalPosition(HorizontalPosition position, Object handler) {
			log.debug("Set horizontal position to {} for the handler {}.",
					position, handler);
		}

	}
}
