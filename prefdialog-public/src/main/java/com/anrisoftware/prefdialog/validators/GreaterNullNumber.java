package com.anrisoftware.prefdialog.validators;

public class GreaterNullNumber implements Validator<Number> {

	@Override
	public boolean isValid(Number value) {
		return value.intValue() > 0;
	}

}
