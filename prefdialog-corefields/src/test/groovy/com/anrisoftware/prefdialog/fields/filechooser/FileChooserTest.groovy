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
package com.anrisoftware.prefdialog.fields.filechooser

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserBean.*
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*
import static java.util.regex.Pattern.compile

import java.awt.Insets

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError
import com.anrisoftware.globalpom.textposition.TextPosition
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.resources.images.api.IconSize
import com.anrisoftware.resources.images.api.Images
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FileChooserField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileChooserTest {

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = INITIAL_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({
            Thread.sleep 60*1000
            assert false : "manually test."
        })
    }

    @Test
    void "null value"() {
        def title = "$NAME::null value"
        def fieldName = NULL_VALUE
        shouldFailWith(ReflectionError) {
            def field = factory.create(bean, fieldName)
        }
    }

    @Test
    void "no model"() {
        def title = "$NAME::no model"
        def fieldName = NO_MODEL
        shouldFailWith(NullPointerException) {
            def field = factory.create(bean, fieldName)
        }
    }

    @Test
    void "with initial value"() {
        def title = "$NAME::with initial value"
        def fieldName = INITIAL_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.textBox "$fieldName-$FILE_FIELD_NAME" requireText compile(/.*aaa.txt/)
        })
    }

    @Test
    void "restore input"() {
        def title = "$NAME::restore input"
        def fieldName = INITIAL_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def tmpfileA = tmp.newFile "fileA"
        def fieldFix
        def openFileChooser

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fieldFix = fixture.textBox "$fieldName-$FILE_FIELD_NAME"
            openFileChooser = fixture.button "$fieldName-$OPEN_FILE_CHOOSER_NAME"
        }, {
            fieldFix.requireText compile(/.*aaa.txt/)
            fieldFix.selectAll()
            fieldFix.enterText tmpfileA.absolutePath
            openFileChooser.focus()
        }, { assert bean.initialValue == tmpfileA }, {
            field.restoreInput()
            openFileChooser.focus()
            fieldFix.requireText compile(/.*aaa.txt/)
        })
    }

    @Test
    void "button icon resource"() {
        def title = "$NAME::button icon resource"
        def fieldName = BUTTON_ICON_RESOURCE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        field.images = images
        field.openFileChooser.setContentAreaFilled false
        field.openFileChooser.setBorderPainted true
        field.openFileChooser.setMargin new Insets(0, 4, 0, 4)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.textBox "$fieldName-$FILE_FIELD_NAME" requireText compile(/.*aaa.txt/)
        })
    }

    @Test
    void "button icon size"() {
        def title = "$NAME::button icon size"
        def fieldName = BUTTON_ICON_RESOURCE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        field.images = images
        field.openFileChooser.setContentAreaFilled false
        field.openFileChooser.setBorderPainted true
        field.openFileChooser.setMargin new Insets(0, 4, 0, 4)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.textBox "$fieldName-$FILE_FIELD_NAME" requireText compile(/.*aaa.txt/)
        },
        { field.setIconSize IconSize.HUGE },
        { field.setIconSize IconSize.LARGE },
        { field.setIconSize IconSize.MEDIUM },
        { field.setIconSize IconSize.SMALL })
    }

    @Test
    void "button text position"() {
        def title = "$NAME::button text position"
        def fieldName = BUTTON_ICON_RESOURCE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        field.images = images
        field.openFileChooser.setContentAreaFilled false
        field.openFileChooser.setBorderPainted true
        field.openFileChooser.setMargin new Insets(0, 4, 0, 4)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixture.textBox "$fieldName-$FILE_FIELD_NAME" requireText compile(/.*aaa.txt/)
        },
        , { field.setButtonTextPosition(TextPosition.ICON_ONLY) }
        , { field.setButtonTextPosition(TextPosition.TEXT_ONLY) }
        , { field.setButtonTextPosition(TextPosition.ICON_ONLY) }
        , { field.setButtonTextPosition(TextPosition.TEXT_ALONGSIDE_ICON) })
    }

    static Injector injector

    static FileChooserFieldFactory factory

    static final String NAME = FileChooserTest.class.simpleName

    static Images images

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    FileChooserBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(new CoreFieldComponentModule(), new FileChooserModule())
        this.factory = injector.getInstance FileChooserFieldFactory
        this.images = createImagesResource(injector)
    }

    @Before
    void setupBean() {
        this.bean = new FileChooserBean()
    }
}
