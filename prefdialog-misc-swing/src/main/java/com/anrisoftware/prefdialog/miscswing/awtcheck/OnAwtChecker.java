package com.anrisoftware.prefdialog.miscswing.awtcheck;

import static javax.swing.SwingUtilities.isEventDispatchThread;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.inject.Inject;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.ConstructorInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Checks the invocation of methods and constructors if they are called on the
 * AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class OnAwtChecker implements MethodInterceptor, ConstructorInterceptor {

	@Inject
	private OnAwtCheckerLogger log;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		if (!isEventDispatchThread()) {
			log.errorAWT(method);
		}
		return invocation.proceed();
	}

	@Override
	public Object construct(ConstructorInvocation invocation) throws Throwable {
		Constructor<?> ctor = invocation.getConstructor();
		if (!isEventDispatchThread()) {
			log.errorAWT(ctor);
		}
		return invocation.proceed();
	}
}
