package com.anrisoftware.prefdialog.miscswing.actions;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractResourcesAction}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class AbstractResourcesActionLogger extends AbstractLogger {

	private static final String NO_RESOURCE = "No resource '{}_{}'.";

	/**
	 * Creates a logger for {@link AbstractResourcesAction}.
	 */
	public AbstractResourcesActionLogger() {
		super(AbstractResourcesAction.class);
	}

	void noResource(AbstractResourcesAction action, String suffix) {
		log.warn(NO_RESOURCE, action.getName(), suffix);
	}

}
