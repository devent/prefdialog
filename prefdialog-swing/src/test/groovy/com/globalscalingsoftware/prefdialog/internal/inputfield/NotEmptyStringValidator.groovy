package com.globalscalingsoftware.prefdialog.internal.inputfield


import com.globalscalingsoftware.prefdialog.Validator;

class NotEmptyStringValidator implements Validator<String> {
	
	@Override
	public boolean isValid(String value) {
		return !value.isEmpty();
	}
}
