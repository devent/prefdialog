package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FormattedTextField;
import com.globalscalingsoftware.prefdialog.TextField;

public class PreferencePanelController {

	private final PreferencePanel preferencePanel;

	private final AnnotationDiscovery annotationDiscovery;

	private final AnnotationsFilter annotationsFilter;

	PreferencePanelController(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter, PreferencePanel preferencePanel) {
		this.preferencePanel = preferencePanel;
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
	}

	public void setField(Object parentValue, Field field) {
		preferencePanel.setTitle(parentValue.toString());
		discoverAnnotations(parentValue);
		setupActions();
	}

	private void setupActions() {
		preferencePanel.setApplyEvent(new Runnable() {

			@Override
			public void run() {
				preferencePanel.applyAllInput();
			}
		});
	}

	private void discoverAnnotations(final Object parentObject) {
		annotationDiscovery.discover(parentObject, annotationsFilter,
				new DiscoveredListener() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						if (a instanceof FormattedTextField) {
							createFormattedTextField(parentObject, field, value);
						} else if (a instanceof TextField) {
							createTextField(parentObject, field, value);
						}

					}
				});
	}

	private void createTextField(Object parentObject, Field field, Object value) {
		preferencePanel.addTextField(parentObject, field, value);
	}

	private void createFormattedTextField(Object parentObject, Field field,
			Object value) {
		preferencePanel.addFormattedTextField(parentObject, field, value);
	}

}
