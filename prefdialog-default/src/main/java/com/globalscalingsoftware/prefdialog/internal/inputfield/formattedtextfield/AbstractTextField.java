package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public abstract class AbstractTextField extends
		AbstractInputField<TextFieldPanel> {

	public AbstractTextField(IReflectionToolbox reflectionToolbox,
			Object value, Field field, ValidatingTextField<?> textField) {
		super(reflectionToolbox, value, field, new TextFieldPanel(textField));
		getComponent().setValue(value);
		getComponent().setFieldName(getFieldName());
		getComponent().setValidator(reflectionToolbox.getValidator(field));
		getComponent().addValidListener(new ValidListener() {

			@Override
			public void validChanged(ValidEvent validEvent) {
				if (validEvent.isEditValid()) {
					getComponent().clearHelpText();
				} else {
					String helpText = getHelpText();
					getComponent().setHelpText(helpText);
				}
			}
		});
	}

	@Override
	public Object getValue() {
		return getComponent().getValue();
	}

}
