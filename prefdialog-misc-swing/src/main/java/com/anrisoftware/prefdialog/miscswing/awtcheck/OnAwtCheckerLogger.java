package com.anrisoftware.prefdialog.miscswing.awtcheck;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link OnAwtChecker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class OnAwtCheckerLogger extends AbstractLogger {

	private static final String AWT_THREAD1 = "Method {}#{} called not on AWT thread";
	private static final String CTOR_THREAD1 = "Constructor {}#{} called not on AWT thread";

	/**
	 * Creates a logger for {@link OnAwtChecker}.
	 */
	public OnAwtCheckerLogger() {
		super(OnAwtChecker.class);
	}

	void errorAWT(Method method) {
		error(AWT_THREAD1, method.getDeclaringClass(), method.getName());
	}

	void errorAWT(Constructor<?> ctor) {
		error(CTOR_THREAD1, ctor.getDeclaringClass(), ctor.getName());
	}

}
