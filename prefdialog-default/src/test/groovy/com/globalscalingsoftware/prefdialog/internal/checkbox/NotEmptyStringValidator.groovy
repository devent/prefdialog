package com.globalscalingsoftware.prefdialog.internal.checkbox

import com.globalscalingsoftware.prefdialog.IValidator;

class NotEmptyStringValidator implements IValidator<String> {
	
	@Override
	public boolean isValid(String value) {
		return !value.isEmpty();
	}
}
