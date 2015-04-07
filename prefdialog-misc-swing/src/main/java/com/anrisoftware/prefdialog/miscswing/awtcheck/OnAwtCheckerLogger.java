/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
