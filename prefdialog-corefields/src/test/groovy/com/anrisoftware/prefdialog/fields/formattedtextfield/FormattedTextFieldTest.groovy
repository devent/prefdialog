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
package com.anrisoftware.prefdialog.fields.formattedtextfield

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.formattedtextfield.FormattedTextFieldBean.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import java.awt.event.KeyEvent

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FormattedTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FormattedTextFieldTest {

    @Test
    void "null value"() {
        def title = "$NAME::null value string"
        def fieldName = NULL_VALUE
        def field = factory.create(bean, fieldName)
        def fieldFix
        def container = field.getAWTComponent()
        def textA = "Text A"
        def textB = "Text B"

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fieldFix = fixture.textBox fieldName
            fieldFix.requireVisible()
            fieldFix.requireText ""
        }, {
            fieldFix.enterText textA
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean.nullStringValue == textA
        }, {
            fieldFix.deleteText()
            fieldFix.enterText textB
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            field.restoreInput()
            fieldFix.requireText ""
            assert bean.nullStringValue == ""
        })
    }

    @Test
    void "with initial value"() {
        def title = "$NAME::with initial value"
        def fieldName = INITIAL_VALUE
        def field = factory.create(bean, fieldName)
        def fieldFix
        def container = field.getAWTComponent()
        def textB = "Text B"

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fieldFix = fixture.textBox fieldName
            fieldFix.requireVisible()
            fieldFix.requireText "Text"
        }, {
            fieldFix.deleteText()
            fieldFix.enterText textB
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            field.restoreInput()
            fieldFix.requireText "Text"
            assert bean.initialStringValue == "Text"
        })
    }

    @Test
    void "not editable"() {
        def title = "$NAME::not editable"
        def fieldName = NOT_EDITABLE
        def field = factory.create(bean, fieldName)
        def fieldFix
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fieldFix = fixture.textBox fieldName
            fieldFix.requireVisible()
            fieldFix.requireNotEditable()
            fieldFix.requireText "Not Editable"
        }, { FrameFixture fixture ->
            field.setEditable true
            fieldFix.requireEditable()
        }, { FrameFixture fixture ->
            field.setEditable false
            fieldFix.requireNotEditable()
        })
    }

    @Test
    void "validated"() {
        def title = "$NAME::validated"
        def fieldName = VALIDATED
        def field = factory.create(bean, fieldName)
        def fieldFix
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fieldFix = fixture.textBox fieldName
            fieldFix.requireVisible()
            fieldFix.requireText VALIDATED_VALID_VALUE
        }, {
            fieldFix.deleteText()
            fieldFix.enterText VALIDATED_VALID_VALUE
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            fieldFix.requireText VALIDATED_VALID_VALUE
            assert bean.validated == VALIDATED_VALID_VALUE
        }, {
            fieldFix.deleteText()
            fieldFix.enterText VALIDATED_INVALID_VALUE
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            fieldFix.requireText VALIDATED_INVALID_VALUE
            assert bean.validated == VALIDATED_VALID_VALUE
        }, {
            fieldFix.deleteText()
            fieldFix.enterText VALIDATED_VALID_VALUE
            fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            fieldFix.requireText VALIDATED_VALID_VALUE
            assert bean.validated == VALIDATED_VALID_VALUE
        })
    }

    static Injector injector

    static FormattedTextFieldFactory factory

    static Texts texts

    static final String NAME = FormattedTextFieldTest.class.simpleName

    FormattedTextFieldBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new FormattedTextFieldModule())
        this.factory = injector.getInstance FormattedTextFieldFactory
        this.texts = createTextsResource(injector)
    }

    @Before
    void setupBean() {
        this.bean = new FormattedTextFieldBean()
    }
}
