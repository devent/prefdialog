package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.globalscalingsoftware.prefdialog.validators.Validator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(Class<?> clazz);

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
