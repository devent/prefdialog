package com.anrisoftware.prefdialog.panel.inputfields.textfield.shared;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.anrisoftware.prefdialog.validators.Validator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new formatted text field {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new ormatted text field {@link Logger} for the given
	 * {@link Class}.
	 */
	Logger create(Class<?> clazz);

	/**
	 * Log messages for the ormatted text field.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	public class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setValidator(Validator<?> validator, Object handler) {
			log.debug("Set the validator {} for the handler {}.", validator,
					handler);
		}

		void editValidClearValidatorText(Object handler) {
			log.debug(
					"The edit is valid, clear the validator text for the handler {}.",
					handler);
		}

		void setValidatorText(String validatorText, Object handler) {
			log.debug("Set the validator text ``{}'' for the handler {}.",
					validatorText, handler);
		}

	}
}
