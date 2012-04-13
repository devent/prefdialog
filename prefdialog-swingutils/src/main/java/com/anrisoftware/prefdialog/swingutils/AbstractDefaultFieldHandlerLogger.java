package com.anrisoftware.prefdialog.swingutils;

import static java.lang.String.format;

/**
 * Log messages for the swing-utils classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class AbstractDefaultFieldHandlerLogger extends AbstractSwingLogger {

	AbstractDefaultFieldHandlerLogger() {
		super(AbstractDefaultFieldHandler.class);
	}

	void setReadOnly(boolean readonly, Object handler) {
		log.debug("Set read only to {} for the handler {}.", readonly, handler);
	}

	void setToolTipText(String text, Object handler) {
		log.debug("Set tool-tip text to ``{}'' for the handler {}.", text,
				handler);
	}

	void setShowToolTip(boolean show, Object handler) {
		log.debug("Set show tool-tip to {} for the handler {}.", show, handler);
	}

	void applyInput(Object value, Object handler) {
		log.debug("Apply the input ``{}'' for the handler {}.", value, handler);
	}

	void restoreInput(Object value, Object handler) {
		log.debug("Restore the input ``{}'' for the handler {}.", value,
				handler);
	}

	NullPointerException noFieldWithName(String name) {
		NullPointerException e = new NullPointerException(format(
				"No such field ``%s'' found.", name));
		log.error("", e);
		return e;
	}
}
