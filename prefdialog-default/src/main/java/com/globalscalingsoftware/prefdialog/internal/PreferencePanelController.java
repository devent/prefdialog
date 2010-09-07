package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IFormattedTextField;
import com.globalscalingsoftware.prefdialog.IFormattedTextFieldFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IPreferencePanelController;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferencePanelController implements IPreferencePanelController {

	private final IPreferencePanel preferencePanel;

	private final IAnnotationDiscovery annotationDiscovery;

	private final Object parentValue;

	private final Field field;

	private final IFormattedTextFieldFactory fieldsFactory;

	private final Map<Field, IInputField> inputFields;

	private final IReflectionToolbox reflectionToolbox;

	@Inject
	PreferencePanelController(IAnnotationDiscovery annotationDiscovery,
			IReflectionToolbox reflectionToolbox,
			IPreferencePanel preferencePanel,
			IFormattedTextFieldFactory fieldsFactory,
			@Assisted("parentValue") Object parentValue,
			@Assisted("field") Field field) {
		this.preferencePanel = preferencePanel;
		this.annotationDiscovery = annotationDiscovery;
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
		this.parentValue = parentValue;
		this.field = field;
		inputFields = new HashMap<Field, IInputField>();
	}

	@Override
	public JPanel getPanel() {
		preferencePanel.setTitle(parentValue.toString());
		discoverAnnotations(parentValue);
		setupActions();
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
		annotationDiscovery.discover(parentObject, new IDiscoveredListener() {

			@Override
			public void fieldAnnotationDiscovered(Field field, Object value,
					Annotation a) {
				if (a instanceof FormattedTextField) {
					createFormattedTextField(parentObject, field, value);
				} else if (a instanceof TextField) {
					createTextField(parentObject, field, value);
				}

			}
		});
	}

	private void createTextField(Object parentObject, Field field, Object value) {
		// TODO
	}

	private void createFormattedTextField(Object parentObject, Field field,
			Object value) {
		String fieldName = field.getName();
		String helpText = reflectionToolbox.getHelpText(field);
		IFormattedTextField textfield = fieldsFactory.createFormattedTextField(
				value, fieldName, helpText);
		preferencePanel.addField(textfield.getComponent());
		inputFields.put(field, textfield);
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
