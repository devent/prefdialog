package com.anrisoftware.prefdialog.dialog.childrenpanels;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link DefaultChildrenPanels}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class DefaultChildrenPanelsLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link DefaultChildrenPanels}.
	 */
	DefaultChildrenPanelsLogger() {
		super(DefaultChildrenPanels.class);
	}

	void preferencePanelCreated(String childName) {
		log.debug("Preference panel for ``{}'' created.", childName);
	}
}
