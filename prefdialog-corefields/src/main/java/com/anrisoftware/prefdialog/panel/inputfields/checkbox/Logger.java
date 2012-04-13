package com.anrisoftware.prefdialog.panel.inputfields.checkbox;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;

/**
 * Log messages for the {@link CheckBoxPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class Logger extends AbstractSwingLogger {

	Logger() {
		super(CheckBoxPanel.class);
	}

	void setText(String text) {
		log.debug("Set check box text to ``{}''.", text);
	}

}
