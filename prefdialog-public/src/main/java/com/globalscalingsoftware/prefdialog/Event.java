package com.globalscalingsoftware.prefdialog;

public interface Event<T> {

	void call(T object);
}
