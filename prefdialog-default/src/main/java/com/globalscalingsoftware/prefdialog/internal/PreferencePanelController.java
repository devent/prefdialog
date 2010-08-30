package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

class PreferencePanelController {

	private final IPreferencePanel preferencePanel;

	private final IAnnotationDiscovery annotationDiscovery;

	private final AnnotationsFilter annotationsFilter;

	public PreferencePanelController(IAnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter, IPreferencePanel preferencePanel) {
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
				new IDiscoveredListener() {

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
