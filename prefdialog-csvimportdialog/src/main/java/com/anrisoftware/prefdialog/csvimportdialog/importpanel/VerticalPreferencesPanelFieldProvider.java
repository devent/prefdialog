package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static java.util.ServiceLoader.load;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.fields.FieldService;

/**
 * Provides the vertical preferences panel field service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class VerticalPreferencesPanelFieldProvider implements Provider<FieldService> {

	private static final String VERTICAL_PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	@Inject
	private VerticalPreferencesPanelFieldProviderLogger log;

	private final FieldService service;

	VerticalPreferencesPanelFieldProvider() {
		this.service = findService();
	}

	@Override
	public FieldService get() {
		return service;
	}

	private FieldService findService() {
		for (FieldService service : load(FieldService.class)) {
			if (service.getInfo().getAnnotationType().getSimpleName()
					.equals(VERTICAL_PREFERENCES_PANEL_NAME)) {
				return service;
			}
		}
		throw log.noService(this, VERTICAL_PREFERENCES_PANEL_NAME);
	}

}
