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
package com.anrisoftware.prefdialog.fields.listbox

import static com.anrisoftware.prefdialog.fields.listbox.ListBoxBean.*
import static com.anrisoftware.prefdialog.fields.listbox.ListBoxService.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ListBoxBean
 * @see ListBoxField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ListBoxTest {

    @Test
    void "array elements with null value"() {
        def title = "$NAME::array elements with null value"
        def fieldName = ARRAY_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName requireNoSelection()
            fixture.list fieldName selectItem 0
            assert bean."$fieldName" == "One"
            fixture.list fieldName selectItem 1
            assert bean."$fieldName" == "Two"
            fixture.list fieldName selectItem 2
            assert bean."$fieldName" == "Three"
        })
    }

    @Test
    void "restore input"() {
        def title = "$NAME::restore input"
        def fieldName = ARRAY_ELEMENTS_SECOND
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName requireSelection "Two"
            fixture.list fieldName selectItem 0
            fixture.list fieldName selectItem 2
            field.restoreInput()
            fixture.list fieldName requireSelection "Two"
            assert bean."$fieldName" == "Two"
        })
    }

    @Test
    void "array elements with second value selected"() {
        def title = "$NAME::array elements with second value selected"
        def fieldName = ARRAY_ELEMENTS_SECOND
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName requireSelection "Two"
        })
    }

    @Test
    void "list elements with null value"() {
        def title = "$NAME::list elements with null value"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            assert bean."$fieldName" == "One"
            fixture.list fieldName selectItem 1
            assert bean."$fieldName" == "Two"
            fixture.list fieldName selectItem 2
            assert bean."$fieldName" == "Three"
        })
    }

    @Test
    void "custom model field"() {
        def title = "$NAME::custom model field"
        def fieldName = CUSTOM_MODEL_FIELD
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            assert bean."$fieldName" == "Eins"
            fixture.list fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.list fieldName selectItem 2
            assert bean."$fieldName" == "Drei"
        })
    }

    @Test
    void "custom model field null"() {
        def title = "$NAME::custom model field null"
        def fieldName = CUSTOM_MODEL_FIELD_NULL
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        assert bean.modelFieldNull != null
        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            assert bean."$fieldName" == "Eins"
            fixture.list fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.list fieldName selectItem 2
            assert bean."$fieldName" == "Drei"
        })
    }

    @Test
    void "custom renderer field"() {
        def title = "$NAME::custom renderer field"
        def fieldName = CUSTOM_RENDERER_FIELD
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            fixture.list fieldName requireSelection "ONE"
            assert bean."$fieldName" == "One"
            fixture.list fieldName selectItem 1
            fixture.list fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.list fieldName selectItem 2
            fixture.list fieldName requireSelection "THREE"
            assert bean."$fieldName" == "Three"
        })
    }

    @Test
    void "custom renderer field null"() {
        def title = "$NAME::custom renderer field null"
        def fieldName = CUSTOM_RENDERER_FIELD_NULL
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        assert bean.rendererFieldNull != null
        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            fixture.list fieldName requireSelection "ONE"
            assert bean."$fieldName" == "One"
            fixture.list fieldName selectItem 1
            fixture.list fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.list fieldName selectItem 2
            fixture.list fieldName requireSelection "THREE"
            assert bean."$fieldName" == "Three"
        })
    }

    @Test
    void "custom model class"() {
        def title = "$NAME::custom model class"
        def fieldName = CUSTOM_MODEL_CLASS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            assert bean."$fieldName" == "Eins"
            fixture.list fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.list fieldName selectItem 2
            assert bean."$fieldName" == "Drei"
        })
    }

    @Test
    void "custom renderer class"() {
        def title = "$NAME::custom renderer class"
        def fieldName = CUSTOM_RENDERER_CLASS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.list fieldName selectItem 0
            fixture.list fieldName requireSelection "ONE"
            assert bean."$fieldName" == "One"
            fixture.list fieldName selectItem 1
            fixture.list fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.list fieldName selectItem 2
            fixture.list fieldName requireSelection "THREE"
            assert bean."$fieldName" == "Three"
        })
    }

    static final String NAME = ListBoxTest.class.simpleName

    static Injector injector

    static ListBoxFieldFactory factory

    ListBoxBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new ListBoxModule())
        this.factory = injector.getInstance ListBoxFieldFactory
    }

    @Before
    void setupBean() {
        this.bean = new ListBoxBean()
    }
}
