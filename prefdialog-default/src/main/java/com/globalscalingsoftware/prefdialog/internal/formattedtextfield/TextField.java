package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.ITextField;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class TextField extends AbstractTextField implements ITextField {

	@Inject
	TextField(@Assisted("value") Object value,
			@Assisted("fieldName") String fieldName,
			@Assisted("helpText") String helpText,
			@Assisted("validator") IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingTextField<JTextField>(value,
				validator, new JTextField()));
	}

}
