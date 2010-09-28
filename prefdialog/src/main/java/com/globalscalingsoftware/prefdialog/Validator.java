package com.globalscalingsoftware.prefdialog;

public interface Validator<T> {

	boolean isValid(T value);
}
