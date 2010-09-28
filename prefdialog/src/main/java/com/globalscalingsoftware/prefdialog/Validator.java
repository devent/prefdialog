package com.globalscalingsoftware.prefdialog;

public interface Validator<T> {

	public static final String EMPTY_STRING = "";

	public static class AlwaysValidVaidator implements Validator<Object> {

		@Override
		public boolean isValid(Object value) {
			return true;
		}

	}

	boolean isValid(T value);
}
