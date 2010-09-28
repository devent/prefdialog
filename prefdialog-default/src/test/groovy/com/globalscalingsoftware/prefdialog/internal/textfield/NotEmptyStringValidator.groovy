package com.globalscalingsoftware.prefdialog.internal.textfield

import com.globalscalingsoftware.prefdialog.Validator;

class NotEmptyStringValidator implements Validator<String> {
	
	@Override
	public boolean isValid(String value) {
		return !value.trim().isEmpty();
	}
}
