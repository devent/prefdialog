package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import static java.lang.String.format;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.Validator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

@SuppressWarnings("serial")
class TextFieldPanel extends AbstractLabelFieldPanel<JTextField> {

	private final ValidatingTextField<?> textField;

	private String fieldName;

	public TextFieldPanel(ValidatingTextField<?> textField) {
		super(textField.getField());
		this.textField = textField;
	}

	@Override
	public void setFieldName(String name) {
		this.fieldName = name;
		super.setFieldName(name);
	}

	public void setValidatorText(String validatorText) {
		String text = format("%s (%s): ", fieldName, validatorText);
		setLabelText(text);
	}

	public void clearValidatorText() {
		String text = format("%s: ", fieldName);
		setLabelText(text);
	}

	@Override
	public Object getValue() {
		return textField.getValue();
	}

	@Override
	public void setValue(Object value) {
		textField.setValue(value);
	}

	public void addValidListener(ValidListener l) {
		textField.addValidListener(l);
	}

	public void setValidator(Validator<?> validator) {
		textField.setValidator(validator);
	}

}
