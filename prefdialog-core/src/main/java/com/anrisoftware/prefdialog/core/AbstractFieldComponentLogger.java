package com.anrisoftware.prefdialog.core;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.fields.FieldComponent;

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

	void checkName(AbstractFieldComponent<?> field, String name) {
		notNull(name, "The name of the field %s cannot be null.", field);
	}

	void checkField(AbstractFieldComponent<?> field,
			FieldComponent<?> childField) {
		notNull(childField, "The child field %s cannot be null.", field);
	}

	void checkWidth(AbstractFieldComponent<?> field, Number width) {
		notNull(width, "The width of the field %s cannot be null.", field);
	}

	void checkValue(AbstractFieldComponent<?> field, Object value) {
		notNull(value, "The value of the field %s cannot be null.", field);
	}
}
