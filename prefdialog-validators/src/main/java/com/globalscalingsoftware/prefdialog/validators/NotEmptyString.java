package com.globalscalingsoftware.prefdialog.validators;

import com.globalscalingsoftware.prefdialog.Validator;

public class NotEmptyString implements Validator<String> {

	@Override
	public boolean isValid(String value) {
		return value != null && !value.trim().isEmpty();
	}

}
