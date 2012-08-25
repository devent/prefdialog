package com.anrisoftware.prefdialog.core;

import static java.lang.String.format;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractFieldComponent}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AbstractFieldComponentLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link AbstractFieldComponent}.
	 */
	AbstractFieldComponentLogger() {
		super(AbstractFieldComponent.class);
	}

	void nameSet(AbstractFieldComponent<?> field, String name) {
		log.trace("Set name to ``{}'' for field {}.", name, field);
	}

	void titleSet(AbstractFieldComponent<?> field, String title) {
		log.trace("Set title to ``{}'' for field {}.", title, field);
	}

	void valueSet(AbstractFieldComponent<?> field, Object value) {
		log.trace("Set value to {} for field {}.", value, field);
	}

	void enabledSet(AbstractFieldComponent<?> field, boolean enabled) {
		log.trace("Set enabled to {} for field {}.", enabled, field);
	}

	void widthSet(AbstractFieldComponent<?> field, Number width) {
		log.trace("Set width to {} for field {}.", width, field);
	}

	void inputIsValid(AbstractFieldComponent<?> field, boolean valid) {
		log.trace("Input is valid {} for field {}.", valid, field);
	}

	NullPointerException noChildFieldFound(AbstractFieldComponent<?> field,
			String name) {
		NullPointerException ex = new NullPointerException(format(
				"No child field with the name ``%s'' found in the field %s.",
				name, field));
		return ex;
	}
}
