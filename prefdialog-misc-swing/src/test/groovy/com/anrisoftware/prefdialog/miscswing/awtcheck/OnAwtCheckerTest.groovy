/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.awtcheck

import groovy.transform.CompileStatic

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see OnAwtChecker
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@CompileStatic
class OnAwtCheckerTest {

    @Test
    void "awt class"() {
        def foo = injector.getInstance(FooAwtClass)
        foo.method()
        foo.methodAwt()
        try {
            foo.methodException()
        } catch (NullPointerException e) {
        }
    }

    @OnAwt
    static class FooAwtClass {

        void method() {
        }

        @OnAwt
        void methodAwt() {
        }

        void methodException() {
            throw new NullPointerException()
        }
    }

    static Injector injector

    @BeforeClass
    static void createInjector() {
        this.injector = Guice.createInjector(new OnAwtCheckerModule())
    }
}
