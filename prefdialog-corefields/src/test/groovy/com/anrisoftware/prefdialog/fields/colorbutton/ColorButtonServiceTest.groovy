/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.colorbutton

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.fields.colorbutton.ColorButtonBean.*
import static com.anrisoftware.prefdialog.fields.colorbutton.ColorButtonService.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice

/**
 * @see ColorButtonService
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ColorButtonServiceTest {

    @Test
    void "service"() {
        def title = "$NAME::service"
        def fieldName = COLOR_BLACK
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({})
    }

    static final String NAME = ColorButtonServiceTest.class.simpleName

    static ColorButtonFieldFactory factory

    ColorButtonBean bean

    @BeforeClass
    static void setupFactories() {
        def injector = Guice.createInjector(new CoreFieldComponentModule())
        this.factory = findService(INFO).getFactory(injector)
    }

    @Before
    void setupBean() {
        this.bean = new ColorButtonBean()
    }
}
