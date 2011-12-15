package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new file chooser field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new file chooser field {@link Logger} for the given
	 * {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the file chooser field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void openFileChooserDialog(Object handler) {
			log.debug("Open file chooser dialog for the handler {}.", handler);
		}

	}
}
