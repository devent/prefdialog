package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.awt.Component;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public abstract class AbstractInputField<ComponentType extends Component>
		implements IInputField {

	private final ComponentType component;

	private final IReflectionToolbox reflectionToolbox;

	private final Object value;

	private final Field field;

	public AbstractInputField(IReflectionToolbox reflectionToolbox,
			Object value, Field field, ComponentType component) {
		this.reflectionToolbox = reflectionToolbox;
		this.value = value;
		this.field = field;
		this.component = component;
	}

	public String getFieldName() {
		return reflectionToolbox.getFieldName(field);
	}

	public String getHelpText() {
		return reflectionToolbox.getHelpText(field);
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public ComponentType getComponent() {
		return component;
	}

}
