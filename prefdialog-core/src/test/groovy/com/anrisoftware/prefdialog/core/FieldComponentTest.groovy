/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-core.
 *
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import static com.anrisoftware.globalpom.textposition.TextPosition.*
import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.core.Preferences.*
import static com.anrisoftware.resources.images.api.IconSize.*

import java.awt.Component

import javax.swing.Icon

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.images.api.IconSize
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.texts.api.Texts
import com.google.inject.Injector

/**
 * Test the field component.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FieldComponentTest {

    @Test
    void "show text field"() {
        def title = "FieldComponentTest :: show text field"
        def preferenceField = preferencesTextField
        def field = factory.create(component, preferences, preferenceField)

        assertField field,
        name: preferenceField,
        title: preferenceField
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with title"() {
        def title = "FieldComponentTest :: with title"
        def preferenceField = preferencesTextFieldWithTitle
        def field = factory.create(component, preferences, preferenceField)
        assertField field,
        name: preferenceField,
        title: "Test Field"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with title resource"() {
        def title = "FieldComponentTest :: with title resource"
        def preferenceField = preferencesTextFieldWithTitleResource
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "Test Field Eng"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with title resource change locale"() {
        def title = "FieldComponentTest :: with title resource change locale"
        def preferenceField = preferencesTextFieldWithTitleResource
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "Test Field Eng"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        }, {
            field.setLocale Locale.GERMAN
            assert field.title == "Test Field Deu"
        })
    }

    @Test
    void "with title resource de"() {
        def title = "FieldComponentTest :: with title resource de"
        def preferenceField = preferencesTextFieldWithTitleResourceDe
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "Test Field Deu",
        locale: Locale.GERMAN
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with title missing resource"() {
        def title = "FieldComponentTest :: with title missing resource"
        def preferenceField = preferencesTextFieldWithTitleMissingResource
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "missing_test_field"
    }

    @Test
    void "read-only"() {
        def title = "FieldComponentTest :: read-only"
        def preferenceField = preferencesTextFieldReadOnly
        def field = factory.create(component, preferences, preferenceField)

        assertField field,
        name: preferenceField,
        title: preferenceField,
        readOnly: true
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireDisabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with tool-tip"() {
        def title = "FieldComponentTest :: with tool-tip"
        def preferenceField = preferencesTextFieldWithToolTip
        def field = factory.create(component, preferences, preferenceField)

        assertField field,
        name: preferenceField,
        title: preferenceField,
        toolTip: "Tool Tip"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            fixture.label preferenceField requireToolTip("Tool Tip")
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with tool-tip resource"() {
        def title = "FieldComponentTest :: with tool-tip resource"
        def preferenceField = preferencesTextFieldWithToolTipResource
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "Test Field Eng",
        toolTip: "Tool Tip Eng"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            fixture.label preferenceField requireToolTip("Tool Tip Eng")
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with tool-tip resource change locale"() {
        def title = "FieldComponentTest :: with tool-tip resource change locale"
        def preferenceField = preferencesTextFieldWithToolTipResource
        def field = factory.create(component, preferences, preferenceField)
        field.setTexts(texts)

        assertField field,
        name: preferenceField,
        title: "Test Field Eng",
        toolTip: "Tool Tip Eng"
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            fixture.label preferenceField requireEnabled()
            fixture.label preferenceField requireToolTip("Tool Tip Eng")
            assert fixture.label(preferenceField).target.getName() == preferenceField
        }, { FrameFixture fixture ->
            field.setLocale Locale.GERMAN
            fixture.label preferenceField requireToolTip("Tool Tip Deu")
        })
    }

    @Test
    void "with icon resource"() {
        def title = "FieldComponentTest :: with icon resource"
        def preferenceField = preferencesTextFieldWithIconResource
        def field = factory.create(component, preferences, preferenceField)
        field.setImages(images)

        assertField field,
        name: preferenceField,
        title: preferenceField,
        icon: { Icon icon -> assert icon.iconWidth == 16 },
        iconSize: SMALL
        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            component.icon = field.icon
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        })
    }

    @Test
    void "with icon resource change icon size"() {
        def title = "FieldComponentTest :: with icon resource change icon size"
        def preferenceField = preferencesTextFieldWithIconResource
        def field = factory.create(component, preferences, preferenceField)
        field.setImages(images)

        assertField field,
        name: preferenceField,
        title: preferenceField,
        icon: { Icon icon -> assert icon.iconWidth == 16 },
        iconSize: SMALL

        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            component.icon = field.icon
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        }, {
            field.setIconSize HUGE
            component.icon = field.icon
            assert field.icon.iconWidth == 48
        }, {
            field.setIconSize LARGE
            component.icon = field.icon
            assert field.icon.iconWidth == 32
        }, {
            field.setIconSize MEDIUM
            component.icon = field.icon
            assert field.icon.iconWidth == 22
        }, {
            field.setIconSize SMALL
            component.icon = field.icon
            assert field.icon.iconWidth == 16
        })
    }

    @Test
    void "with icon resource change locale"() {
        def title = "FieldComponentTest :: with icon resource change locale"
        def preferenceField = preferencesTextFieldWithIconResource
        def field = factory.create(component, preferences, preferenceField)
        field.setImages(images)


        assertField field,
        name: preferenceField,
        title: preferenceField,
        icon: { Icon icon -> assert icon.iconWidth == 16 },
        iconSize: SMALL

        new TestFrameUtil(title, field.AWTComponent).withFixture({FrameFixture fixture ->
            component.icon = field.icon
            fixture.label preferenceField requireEnabled()
            assert fixture.label(preferenceField).target.getName() == preferenceField
        }, {
            field.setLocale Locale.GERMAN
            component.icon = field.icon
            assert field.icon.iconWidth == 16
        })
    }

    static Injector injector

    static MockFieldComponentFactory factory

    static Texts texts

    static Images images

    Preferences preferences

    Component component

    @BeforeClass
    static void setupFactories() {
        injector = createInjector()
        factory = injector.getInstance MockFieldComponentFactory
        texts = createTextsResource(injector)
        images = createImagesResource(injector)
    }

    @Before
    void setupPreferences() {
        preferences = new Preferences()
        component = createLabel()
    }
}
