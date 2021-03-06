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
package com.anrisoftware.prefdialog.fields.combobox

import static com.anrisoftware.prefdialog.fields.combobox.ComboBoxBean.*
import static com.anrisoftware.prefdialog.fields.combobox.ComboBoxService.*

import java.awt.event.KeyEvent
import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.fields.utils.FieldTestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ComboBoxBean
 * @see ComboBoxField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ComboBoxTest extends FieldTestUtils {

    @Test
    void "array elements with null value"() {
        def title = "$NAME::array elements with null value"
        def fieldName = ARRAY_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.comboBox fieldName requireSelection "One"
            fixture.comboBox fieldName selectItem 1
            assert bean."$fieldName" == "Two"
            fixture.comboBox fieldName selectItem 2
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
            fixture.comboBox fieldName selectItem 0
            fixture.comboBox fieldName selectItem 2
            field.restoreInput()
            fixture.comboBox fieldName requireSelection "Two"
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
            fixture.comboBox fieldName requireSelection "Two"
        })
    }

    @Test
    void "list elements with null value"() {
        def title = "$NAME::list elements with null value"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.comboBox fieldName requireSelection "One"
            fixture.comboBox fieldName selectItem 1
            assert bean."$fieldName" == "Two"
            fixture.comboBox fieldName selectItem 2
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
            fixture.comboBox fieldName requireSelection "Eins"
            fixture.comboBox fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.comboBox fieldName selectItem 2
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
            fixture.comboBox fieldName requireSelection "Eins"
            fixture.comboBox fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.comboBox fieldName selectItem 2
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
            fixture.comboBox fieldName requireSelection "ONE"
            fixture.comboBox fieldName selectItem 1
            fixture.comboBox fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.comboBox fieldName selectItem 2
            fixture.comboBox fieldName requireSelection "THREE"
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
            fixture.comboBox fieldName requireSelection "ONE"
            fixture.comboBox fieldName selectItem 1
            fixture.comboBox fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.comboBox fieldName selectItem 2
            fixture.comboBox fieldName requireSelection "THREE"
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
            fixture.comboBox fieldName requireSelection "Eins"
            fixture.comboBox fieldName selectItem 1
            assert bean."$fieldName" == "Zwei"
            fixture.comboBox fieldName selectItem 2
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
            fixture.comboBox fieldName requireSelection "ONE"
            fixture.comboBox fieldName selectItem 1
            fixture.comboBox fieldName requireSelection "TWO"
            assert bean."$fieldName" == "Two"
            fixture.comboBox fieldName selectItem 2
            fixture.comboBox fieldName requireSelection "THREE"
            assert bean."$fieldName" == "Three"
        })
    }

    @Test
    void "editable"() {
        def title = "$NAME::editable"
        def fieldName = EDITABLE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def text = "Zwei"

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.comboBox fieldName replaceText text
            fixture.comboBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
            fixture.comboBox fieldName requireSelection text
            assert bean."$fieldName" == text
        })
    }

    @Test
    void "editable, veto value"() {
        def title = "$NAME::editable, veto value"
        def fieldName = EDITABLE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def l = {
            if (it.newValue != "Valid") {
                throw new PropertyVetoException("Not valid", it)
            }
        } as VetoableChangeListener
        field.addVetoableChangeListener(l)
        def comboBox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            comboBox = fixture.comboBox fieldName
            comboBox.replaceText "Some"
            comboBox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == null
        }, {
            comboBox.replaceText "Valid"
            comboBox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "Valid"
        }, {
            // Thread.sleep 60000
        })
    }

    static final String NAME = ComboBoxTest.class.simpleName

    static Injector injector

    static ComboBoxFieldFactory factory

    ComboBoxBean bean

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new ComboBoxModule())
        this.factory = injector.getInstance ComboBoxFieldFactory
    }

    @Before
    void setupBean() {
        this.bean = new ComboBoxBean()
    }
}
