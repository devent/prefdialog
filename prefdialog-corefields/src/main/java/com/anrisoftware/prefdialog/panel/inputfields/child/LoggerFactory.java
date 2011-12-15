package com.anrisoftware.prefdialog.panel.inputfields.child;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new child field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new child field {@link Logger} for the given {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the child field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void fieldHandlerAdded(Object fieldHandler, Object handler) {
			log.debug("Add new field handler {} to the handler {}.",
					fieldHandler, handler);
		}

	}
}
