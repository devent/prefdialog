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

import static com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerLogger._.ctor_thread;
import static com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerLogger._.error_invoke_ctor;
import static com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerLogger._.error_invoke_method;
import static com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerLogger._.method_thread;

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

    enum _ {

        ctor_thread("Constructor {} called not in event dispatch thread."),

        method_thread("Method {} called not in event dispatch thread."),

        error_invoke_method("Error invoke method {}, {}: {}"),

        error_invoke_ctor("Error invoke contructor {}, {}: {}");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Creates a logger for {@link OnAwtChecker}.
     */
    public OnAwtCheckerLogger() {
        super(OnAwtChecker.class);
    }

    void errorAWT(Method method) {
        error(method_thread, method);
    }

    void errorAWT(Constructor<?> ctor) {
        error(ctor_thread, ctor);
    }

    void errorInvokeMethod(Method method, Throwable e) {
        error(error_invoke_method, method, e, e.getLocalizedMessage());
    }

    void errorInvokeCtor(Constructor<?> ctor, Throwable e) {
        error(error_invoke_ctor, ctor, e, e.getLocalizedMessage());
    }

}
