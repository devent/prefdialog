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
package com.anrisoftware.prefdialog.fields.table

import static com.anrisoftware.prefdialog.fields.table.TableBean.*
import static com.anrisoftware.prefdialog.fields.table.TableFieldService.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.fields.utils.FieldTestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see TableBean
 * @see TableField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TableTest extends FieldTestUtils {

    @Test
    void "custom model field"() {
        def title = "$NAME::custom model field"
        def fieldName = "customTableModelField"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom model class"() {
        def title = "$NAME::custom model class"
        def fieldName = "customModelClass"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom string renderer field"() {
        def title = "$NAME::custom string renderer field"
        def fieldName = "stringDefaultRendererField"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom multiple renderers field"() {
        def title = "$NAME::custom multiple renderers field"
        def fieldName = "multipleDefaultRendererField"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom string renderer class"() {
        def title = "$NAME::custom string renderer class"
        def fieldName = "stringDefaultRendererClass"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom multiple renderers classes"() {
        def title = "$NAME::custom multiple renderers classes"
        def fieldName = "multipleDefaultRendererClasses"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    @Test
    void "custom string editor class"() {
        def title = "$NAME::custom string editor class"
        def fieldName = "stringDefaultEditorClass"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()

        frameTestingFactory.create(createComponent: { frame -> container })().withFixture({ //.
            FrameFixture fix ->
            fix.table fieldName requireColumnCount(4)
            fix.table fieldName requireRowCount(4)
        })
    }

    static final String NAME = TableTest.class.simpleName

    static Injector injector

    static TableFieldFactory factory

    static FrameTestingFactory frameTestingFactory

    TableBean bean

    @BeforeClass
    static void setupFactories() {
        this.injector = Guice.createInjector(
                new CoreFieldComponentModule(),
                new TableFieldModule(),
                new FrameTestingModule())
        this.factory = injector.getInstance TableFieldFactory
        this.frameTestingFactory = injector.getInstance FrameTestingFactory
    }

    @Before
    void setupBean() {
        this.bean = injector.getInstance TableBean
    }
}
