package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.LoggerFactory;
import com.globalscalingsoftware.prefdialog.validators.Validator;
import com.google.inject.Inject;

public interface SharedTextFieldLoggerFactory extends LoggerFactory {

	@Override
	SharedTextFieldLogger create(Class<?> clazz);

	public class SharedTextFieldLogger extends Logger {

		@Inject
		protected SharedTextFieldLogger(Class<?> contextClass) {
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
