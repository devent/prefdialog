package com.anrisoftware.prefdialog.miscswing.awtcheck;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import com.google.inject.AbstractModule;

/**
 * Binds the check on AWT interceptor.
 * 
 * @see OnAwt
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class OnAwtCheckerModule extends AbstractModule {

	@Override
	protected void configure() {
		OnAwtChecker checker = new OnAwtChecker();
		requestInjection(checker);
		bindInterceptor(any(), annotatedWith(OnAwt.class), checker);
	}
}
