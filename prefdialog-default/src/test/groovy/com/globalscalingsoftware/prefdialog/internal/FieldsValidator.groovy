package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.IValidator;

class FieldsValidator implements IValidator<Integer> {
	
	@Override
	public boolean isValid(Integer value) {
		return value > 1 && value <= 100;
	}
}
