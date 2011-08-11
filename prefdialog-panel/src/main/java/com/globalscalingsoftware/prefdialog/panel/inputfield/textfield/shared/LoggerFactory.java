package com.globalscalingsoftware.prefdialog.panel.inputfield.textfield.shared;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.globalscalingsoftware.prefdialog.validators.Validator;
import com.google.inject.Inject;

public interface LoggerFactory {

	Logger create(Class<?> clazz);

	public abstract class Logger extends AbstractSwingLogger {

		@Inject
		protected Logger(Class<?> contextClass) {
			super(contextClass);
		}

		public void setValidator(Field field, Validator<?> validator) {
			log.debug("Set the validator {} for the field ``{}''.", validator,
					field);
		}

		public void editValidClearValidatorText(Field field) {
			log.debug(
					"The edit is valid, clear the validator text for the field ``{}''.",
					field);
		}

		public void setValidatorText(Field field, String validatorText) {
			log.debug("Set the validator text ``{}'' for the field ``{}''.",
					validatorText, field);
		}

	}
}
