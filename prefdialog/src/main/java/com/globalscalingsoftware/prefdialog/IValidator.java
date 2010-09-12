package com.globalscalingsoftware.prefdialog;

public interface IValidator<T> {

	public static final String EMPTY_STRING = "";

	public static class AlwaysValidVaidator implements IValidator<Object> {

		@Override
		public boolean isValid(Object value) {
			return true;
		}

	}

	boolean isValid(T value);
}
