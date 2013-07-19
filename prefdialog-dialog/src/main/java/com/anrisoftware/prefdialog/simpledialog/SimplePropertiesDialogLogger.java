package com.anrisoftware.prefdialog.simpledialog;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SimplePropertiesDialog}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class SimplePropertiesDialogLogger extends AbstractLogger {

	private static final String SERVICE_FOUND1 = "No preference panel service found '{}' for dialog {}.";
	private static final String SERVICE_FOUND = "No preference panel service found";

	/**
	 * Creates a logger for {@link SimplePropertiesDialog}.
	 */
	public SimplePropertiesDialogLogger() {
		super(SimplePropertiesDialog.class);
	}

	IllegalStateException errorFindService(SimpleDialog dialog, String name) {
		return logException(new IllegalStateException(SERVICE_FOUND),
				SERVICE_FOUND1, name, dialog);
	}
}
