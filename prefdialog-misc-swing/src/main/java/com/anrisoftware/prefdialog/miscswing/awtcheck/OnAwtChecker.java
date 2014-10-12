/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
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
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object construct(ConstructorInvocation invocation) throws Throwable {
        Constructor<?> ctor = invocation.getConstructor();
        if (!isEventDispatchThread()) {
            log.errorAWT(ctor);
        }
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }
}
