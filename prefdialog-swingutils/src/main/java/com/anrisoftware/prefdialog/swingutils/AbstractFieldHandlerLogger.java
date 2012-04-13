package com.anrisoftware.prefdialog.swingutils;

class AbstractFieldHandlerLogger extends AbstractSwingLogger {

	AbstractFieldHandlerLogger() {
		super(AbstractFieldHandler.class);
	}

	void setWidth(double width, Object handler) {
		log.debug("Set width to {} for the handler {}.", width, handler);
	}

	void setName(String name, Object handler) {
		log.debug("Set name to ``{}'' for the handler {}.", name, handler);
	}

	void setEnabled(boolean enabled, Object handler) {
		log.debug("Set enabled to {} for the handler {}.", enabled, handler);
	}

	void setTitle(String title, Object handler) {
		log.debug("Set the title to ``{}'' for the handler {}.", title, handler);
	}

	void setValue(Object value, Object handler) {
		log.debug("Set value to ``{}'' for the handler {}.", value, handler);
	}

}
