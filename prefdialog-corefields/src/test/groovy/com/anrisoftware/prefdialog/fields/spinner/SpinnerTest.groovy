/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.spinner

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.spinner.SpinnerBean.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import java.awt.event.KeyEvent

import org.fest.swing.fixture.FrameFixture
import org.joda.time.DateTime
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.ProvisionException

/**
 * @see SpinnerField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpinnerTest {

    @Test
    void "null value"() {
        def fieldName = NULL_VALUE
        def title = "$NAME::$fieldName"
        shouldFailWith(ProvisionException) {
            def field = factory.create(bean, fieldName)
        }
    }

    @Test
    void "integer value"() {
        def title = "$NAME::integer value"
        def fieldName = INTEGER_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def valueA = 10
        def valueB = 20
        def spinner

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireVisible()
            spinner.requireValue 0
        }, { FrameFixture fixture ->
            spinner.enterText "$valueA"
            spinner.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean.integerValue == valueA
        }, { FrameFixture fixture ->
            spinner.enterText "$valueB"
            spinner.pressAndReleaseKeys KeyEvent.VK_ENTER
            field.restoreInput()
            spinner.requireValue 0
            assert bean.integerValue == 0
        })
    }

    @Test
    void "integer bounds value"() {
        def title = "$NAME::integer bounds value"
        def fieldName = INTEGER_BOUNDS_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireValue 6
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.integerBoundsValue == 8
        })
    }

    @Test
    void "double bounds value"() {
        def title = "$NAME::double bounds value"
        def fieldName = DOUBLE_BOUNDS_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireValue 6.0d
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.doubleBoundsValue == 6.5d
        })
    }

    @Test
    void "date value"() {
        def title = "$NAME::date value"
        def fieldName = DATE_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner
        def value = DateTime.parse("2005-03-25").toDate()
        def nextValue = DateTime.parse("2005-03-26").toDate()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireValue value
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.dateValue == nextValue
        })
    }

    @Test
    void "date bounds value"() {
        def title = "$NAME::date bounds value"
        def fieldName = DATE_BOUNDS_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner
        def value = DateTime.parse("2005-03-25").toDate()
        def nextValue = DateTime.parse("2005-04-25").toDate()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireValue value
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.dateBoundsValue == nextValue
        })
    }

    @Test
    void "list value"() {
        def title = "$NAME::list value"
        def fieldName = LIST_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner
        def value = "Aa"
        def nextValue = "Bb"

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireValue value
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.listValue == nextValue
        })
    }

    @Test
    void "custom model"() {
        def title = "$NAME::custom model"
        def fieldName = CUSTOM_MODEL
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireVisible()
            spinner.requireValue 2
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.customModel == 4
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.customModel == 6
        })
    }

    @Test
    void "custom model class"() {
        def title = "$NAME::custom model class"
        def fieldName = CUSTOM_MODEL_CLASS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def spinner

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            spinner = fixture.spinner fieldName
            spinner.requireVisible()
            spinner.requireValue 2
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.customModelClass == 4
        }, { FrameFixture fixture ->
            spinner.increment()
            assert bean.customModelClass == 6
        })
    }

    static final String NAME = SpinnerTest.class.simpleName

    static Injector injector

    static SpinnerFieldFactory factory

    static Texts texts

    SpinnerBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new SpinnerModule())
        this.factory = injector.getInstance SpinnerFieldFactory
        this.texts = createTextsResource(injector)
    }

    @Before
    void setupBean() {
        this.bean = new SpinnerBean()
    }
}
