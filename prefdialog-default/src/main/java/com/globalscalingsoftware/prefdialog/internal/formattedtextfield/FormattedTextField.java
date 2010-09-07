package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IFormattedTextField;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class FormattedTextField extends AbstractTextField implements
		IFormattedTextField {

	@Inject
	FormattedTextField(@Assisted("value") Object value,
			@Assisted("fieldName") String fieldName,
			@Assisted("helpText") String helpText,
			@Assisted("validator") IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingFormattedTextField(value,
				validator, new JFormattedTextField()));
	}

}
