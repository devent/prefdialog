/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.radiobutton

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.fields.radiobutton.RadioButtonBean.*
import static com.anrisoftware.prefdialog.fields.radiobutton.RadioButtonService.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice

/**
 * @see RadioButtonService
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class RadioButtonServiceTest {

    @Test
    void "service"() {
        def title = "$NAME::service"
        def fieldName = NO_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({})
    }

    static final String NAME = RadioButtonTest.class.simpleName

    static RadioButtonFieldFactory factory

    RadioButtonBean bean

    @BeforeClass
    static void setupFactories() {
        def injector = Guice.createInjector(new CoreFieldComponentModule())
        this.factory = findService(INFO).getFactory(injector)
    }

    @Before
    void setupBean() {
        this.bean = new RadioButtonBean()
    }
}
