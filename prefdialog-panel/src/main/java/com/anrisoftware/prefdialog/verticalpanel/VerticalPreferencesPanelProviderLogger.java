package com.anrisoftware.prefdialog.verticalpanel;

import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelProviderLogger._.service_found;
import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelProviderLogger._.service_found_message;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link VerticalPreferencesPanelProvider}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class VerticalPreferencesPanelProviderLogger extends AbstractLogger {

	enum _ {

		service_found_message("No service found '{}'."),

		service_found("No vertical preferences panel service found");

		private String name;

		private _(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * Creates a logger for {@link VerticalPreferencesPanelProvider}.
	 */
	public VerticalPreferencesPanelProviderLogger() {
		super(VerticalPreferencesPanelProvider.class);
	}

	IllegalStateException errorFindService(String name) {
		return logException(
				new IllegalStateException(service_found.toString()),
				service_found_message, name);
	}

}
