package com.anrisoftware.prefdialog.swingutils;

import java.io.IOException;
import java.net.URL;

import com.anrisoftware.prefdialog.annotations.TextPosition;

/**
 * Log messages for the {@link AbstractLabelFieldHandler}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class AbstractLabelFieldHandlerLogger extends AbstractSwingLogger {

	AbstractLabelFieldHandlerLogger() {
		super(AbstractLabelFieldHandler.class);
	}

	void setupText(TextPosition position, String text) {
		log.debug(
				"Setup the text ``{}'' with the text position {} for the title.",
				text, position);
	}

	void setupButtonIcon(URL iconUrl, Object handler) {
		log.debug("Setup the icon {} for the title for the handler {}.",
				iconUrl, handler);
	}

	void errorLoadIcon(IOException e) {
		log.error("Error load the icon for the title.", e);
	}

	void setShowTitle(boolean show, Object handler) {
		log.debug("Set show title to {} for the handler {}.", show, handler);
	}

}
