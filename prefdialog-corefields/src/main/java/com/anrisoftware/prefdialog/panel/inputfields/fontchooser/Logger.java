package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;

/**
 * Logger for the {@link FontChooserPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class Logger extends AbstractSwingLogger {

	Logger() {
		super(FontChooserPanel.class);
	}

	void setMinimumFontChooserHeight(int height) {
		log.debug("Set minimum font chooser panel height to {}.", height);

	}
}
