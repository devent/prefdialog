package com.anrisoftware.prefdialog.verticalpanel;

import static java.util.ServiceLoader.load;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Provider;

/**
 * Provides the vertical preferences panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
public class VerticalPreferencesPanelProvider implements Provider<FieldService> {

	private static final String PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	private final VerticalPreferencesPanelProviderLogger log;

	private final FieldService service;

	@Inject
	VerticalPreferencesPanelProvider(VerticalPreferencesPanelProviderLogger log) {
		this.log = log;
		this.service = findService();
	}

	@Override
	public FieldService get() {
		return service;
	}

	private FieldService findService() {
		for (FieldService service : load(FieldService.class)) {
			if (isPreferencesPanelService(service)) {
				return service;
			}
		}
		throw log.errorFindService(PREFERENCES_PANEL_NAME);
	}

	private boolean isPreferencesPanelService(FieldService service) {
		return service.getInfo().getAnnotationType().getSimpleName()
				.equals(PREFERENCES_PANEL_NAME);
	}
}
