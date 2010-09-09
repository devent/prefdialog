package com.globalscalingsoftware.prefdialog.internal;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IPreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IPreferencePanelController;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PreferencePanelController implements IPreferencePanelController {

	private final IPreferencePanel preferencePanel;

	private final IAnnotationDiscovery annotationDiscovery;

	private final Object parentValue;

	private final Field field;

	private final IFieldsFactory fieldsFactory;

	private final Map<Field, IInputField> inputFields;

	private final IReflectionToolbox reflectionToolbox;

	private final IPreferencePanelAnnotationFilter filter;

	@Inject
	PreferencePanelController(IAnnotationDiscovery annotationDiscovery,
			IPreferencePanelAnnotationFilter filter,
			IReflectionToolbox reflectionToolbox,
			IPreferencePanel preferencePanel, IFieldsFactory fieldsFactory,
			@Assisted("parentValue") Object parentValue,
			@Assisted("field") Field field) {
		this.preferencePanel = preferencePanel;
		this.annotationDiscovery = annotationDiscovery;
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
		this.parentValue = parentValue;
		this.filter = filter;
		this.field = field;
		inputFields = new HashMap<Field, IInputField>();
	}

	@Override
	public void setupPanel() {
		preferencePanel.setTitle(parentValue.toString());
		discoverAnnotations(parentValue);
		setupActions();
	}

	@Override
	public JPanel getPanel() {
		return preferencePanel.getPanel();
	}

	private void setupActions() {
		preferencePanel.setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyAllInput();
			}
		});
	}

	private void discoverAnnotations(final Object parentObject) {
		annotationDiscovery.discover(filter, parentObject,
				new IDiscoveredListener() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						createInputField(parentObject, field, value);
					}
				});
	}

	private void createInputField(Object parentObject, Field field, Object value) {
		IInputField inputfield = fieldsFactory.createField(parentObject, field,
				value);
		preferencePanel.addField(inputfield.getComponent());
		inputFields.put(field, inputfield);
	}

	@Override
	public void applyAllInput() {
		for (Field field : inputFields.keySet()) {
			IInputField inputField = inputFields.get(field);
			Object value = inputField.getValue();
			reflectionToolbox.setValueTo(field, parentValue, value);
		}
	}

	@Override
	public void undoAllInput() {
		// TODO Auto-generated method stub

	}
}
