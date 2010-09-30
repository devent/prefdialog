package com.globalscalingsoftware.prefdialog.internal.inputfield


import com.globalscalingsoftware.prefdialog.Validator;

class FieldsValidator implements Validator<Integer> {
	
	@Override
	public boolean isValid(Integer value) {
		return value > 1 && value <= 100;
	}
}
