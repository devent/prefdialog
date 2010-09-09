package com.globalscalingsoftware.prefdialog.internal.radiobutton

import com.globalscalingsoftware.prefdialog.IValidator;

class NotEmptyStringValidator implements IValidator<String> {
	
	@Override
	public boolean isValid(String value) {
		return !value.isEmpty();
	}
}
