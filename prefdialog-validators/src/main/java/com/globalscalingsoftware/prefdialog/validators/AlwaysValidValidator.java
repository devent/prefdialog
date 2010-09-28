package com.globalscalingsoftware.prefdialog.validators;

import com.globalscalingsoftware.prefdialog.Validator;

public class AlwaysValidValidator implements Validator<Object> {

	@Override
	public boolean isValid(Object value) {
		return true;
	}

}
