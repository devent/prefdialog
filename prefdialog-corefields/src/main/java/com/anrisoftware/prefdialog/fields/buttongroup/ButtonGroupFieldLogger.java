package com.anrisoftware.prefdialog.fields.buttongroup;

import static java.lang.String.format;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;

/**
 * Logging messages for {@link ButtonGroupField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ButtonGroupFieldLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link ButtonGroupField}.
	 */
	ButtonGroupFieldLogger() {
		super(ButtonGroupField.class);
	}

	IllegalArgumentException valueNotList(Object value) {
		IllegalArgumentException ex = new IllegalArgumentException(format(
				"The value %s is not an array or list of actions.", value));
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	void horizontalAlignmentSet(ButtonGroupField field,
			HorizontalAlignment alignment) {
		log.trace("Set the horizontal alignment to {} in the button group {}.",
				alignment, field);
	}
}
