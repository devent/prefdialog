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

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.radiobutton.RadioButtonBean.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import javax.swing.BoxLayout
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see RadioButton
 * @see RadioButtonField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class RadioButtonTest {

    @Test
    void "null value"() {
        def fieldName = NULL_VALUE
        shouldFailWith(ReflectionError) {
            def field = factory.create(bean, fieldName)
        }
    }

    @Test
    void "apply user input"() {
        def title = "$NAME::apply user input"
        def fieldName = NO_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireNotSelected()
        }, {
            button.click()
            assert bean.noText == true
        }, {
            button.click()
            assert bean.noText == false
        })
    }

    @Test
    void "restore user input"() {
        def title = "$NAME::restore user input"
        def fieldName = NO_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireNotSelected()
        }, {
            button.click()
            field.restoreInput()
            button.requireNotSelected()
            assert bean.noText == false
        }, {
            button.click()
            field.restoreInput()
            button.requireNotSelected()
            assert bean.noText == false
        })
    }

    @Test
    void "no text"() {
        def title = "$NAME::no text"
        def fieldName = NO_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireText NO_TEXT
        })
    }

    @Test
    void "with text"() {
        def title = "$NAME::with text"
        def fieldName = WITH_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireText "Radio Button Text"
        })
    }

    @Test
    void "with text resource"() {
        def title = "$NAME::with text resource"
        def fieldName = WITH_TEXT_RESOURCE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        field.setTexts texts
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireText "Radio Button English"
        }, {
            field.setLocale Locale.GERMAN
            button.requireText "Radio Button Deutsch"
        }, {
            field.setLocale Locale.ENGLISH
            button.requireText "Radio Button English"
        })
    }

    @Test
    void "not showing text"() {
        def title = "$NAME::not showing text"
        def fieldName = NOT_SHOW_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireText ""
        }, { FrameFixture fixture ->
            field.setShowText true
            button.requireText fieldName
        }, { FrameFixture fixture ->
            field.setShowText false
            button.requireText ""
        })
    }

    @Test
    void "read only"() {
        def title = "$NAME::read only"
        def fieldName = READ_ONLY
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.requireDisabled()
        }, {
            field.setEnabled true
            button.requireEnabled()
        }, {
            field.setEnabled false
            button.requireDisabled()
        })
    }

    @Test
    void "with action listener added"() {
        def title = "$NAME::with action listener added"
        def fieldName = WITH_ACTION_LISTENER
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.click()
            assert bean.actionListenerCalled == true
        })
    }

    @Test
    void "with action set"() {
        def title = "$NAME::with action set"
        def fieldName = WITH_ACTION
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.click()
            assert bean.actionListenerCalled == true
        })
    }

    @Test
    void "with action listener class added"() {
        def title = "$NAME::with action listener class added"
        def fieldName = WITH_ACTION_LISTENER_CLASS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.click()
            def action = button.target.getActionListeners().find{ it instanceof CustomActionListener }
            assert action.actionCalled == true
        })
    }

    @Test
    void "with action class added"() {
        def title = "$NAME::with action class added"
        def fieldName = WITH_ACTION_CLASS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def button

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            button = fix.radioButton fieldName
            button.click()
            assert field.getAction().actionCalled == true
        })
    }

    @Test
    void "button group"() {
        def title = "$NAME::button group"
        def fieldA = factory.create(bean, GROUP_MEMBER_A)
        def fieldB = factory.create(bean, GROUP_MEMBER_B)
        def container = new JPanel()
        container.setLayout new BoxLayout(container, BoxLayout.Y_AXIS)
        container.add fieldA.getAWTComponent()
        container.add fieldB.getAWTComponent()
        def buttonA
        def buttonB

        new TestFrameUtil(title, container).withFixture({ FrameFixture fix ->
            buttonA = fix.radioButton GROUP_MEMBER_A
            buttonB = fix.radioButton GROUP_MEMBER_B
        }, {
            buttonA.click()
            assert bean.groupMemberA == true
            assert bean.groupMemberB == false
        }, {
            buttonB.click()
            assert bean.groupMemberA == false
            assert bean.groupMemberB == true
        }, {
            buttonA.click()
            assert bean.groupMemberA == true
            assert bean.groupMemberB == false
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = NO_TEXT
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({
            Thread.sleep 60 * 1000l
            assert false : "manually test"
        })
    }

    static final String NAME = RadioButtonTest.class.simpleName

    static Injector injector

    static RadioButtonFieldFactory factory

    static Texts texts

    RadioButtonBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new RadioButtonModule())
        this.factory = injector.getInstance RadioButtonFieldFactory
        this.texts = createTextsResource(injector)
    }

    @Before
    void setupBean() {
        this.bean = new RadioButtonBean()
    }
}
