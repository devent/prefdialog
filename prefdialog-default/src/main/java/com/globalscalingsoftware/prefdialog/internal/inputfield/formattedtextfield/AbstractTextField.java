package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public abstract class AbstractTextField extends
		AbstractInputField<TextFieldPanel> {

	public AbstractTextField(String fieldName, String helpText,
			ValidatingTextField<?> textField) {
		super(fieldName, helpText, new TextFieldPanel(fieldName, textField));
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
