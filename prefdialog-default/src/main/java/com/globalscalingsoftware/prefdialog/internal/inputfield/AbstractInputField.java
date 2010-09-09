package com.globalscalingsoftware.prefdialog.internal.inputfield;


import java.awt.Component;

import com.globalscalingsoftware.prefdialog.IInputField;

public abstract class AbstractInputField<ComponentType extends Component>
		implements IInputField {

	private final String fieldName;

	private final String helpText;

	private final ComponentType component;

	public AbstractInputField(String fieldName, String helpText,
			ComponentType component) {
		this.fieldName = fieldName;
		this.component = component;
		this.helpText = helpText;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getHelpText() {
		return helpText;
	}

	@Override
	public ComponentType getComponent() {
		return component;
	}

}
