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
package com.anrisoftware.prefdialog.fields.child

import static com.anrisoftware.prefdialog.fields.child.ChildBean.*
import static com.anrisoftware.prefdialog.fields.child.ChildService.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.fields.checkbox.CheckBoxFieldFactory
import com.anrisoftware.prefdialog.fields.checkbox.CheckBoxModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ChildField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ChildTest {

    @Test
    void "title"() {
        def title = "$NAME::title"
        def fieldName = NULL_VALUE
        def separatorName = "$fieldName-$TITLE_SEPARATOR_NAME"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def checkBoxFieldName = CHECKBOX
        def checkBox = checkBoxfactory.create(bean.nullValue, checkBoxFieldName)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            field.addField checkBox
            fixture.panel fieldName requireVisible()
            fixture.checkBox checkBoxFieldName requireVisible()
        })
    }

    @Test
    void "no title"() {
        def title = "ChildTest::no title"
        def fieldName = NO_TITLE
        def separatorName = "$fieldName-$TITLE_SEPARATOR_NAME"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def checkBoxFieldName = CHECKBOX
        def checkBox = checkBoxfactory.create(bean.noTitle, checkBoxFieldName)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            field.addField checkBox
            fixture.panel fieldName requireVisible()
            fixture.checkBox checkBoxFieldName requireVisible()
        })
    }

    @Test
    void "order children"() {
        def title = "$NAME::order children"
        def fieldName = NULL_VALUE
        def separatorName = "$fieldName-$TITLE_SEPARATOR_NAME"
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def checkBoxA = checkBoxfactory.create(bean.nullValue, CHECKBOX)
        def checkBoxB = checkBoxfactory.create(bean.nullValue, CHECKBOX_ORDER)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            field.addField checkBoxA
            field.addField checkBoxB
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = NULL_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            Thread.sleep 60*1000
            assert false : "manually test"
        })
    }

    static final String NAME = ChildTest.class.simpleName

    static Injector injector

    static ChildFieldFactory factory

    static CheckBoxFieldFactory checkBoxfactory

    ChildBean bean

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = Guice.createInjector(new CoreFieldComponentModule())
        this.factory = createChildFieldFactory injector
        this.checkBoxfactory = createCheckBoxFieldFactory injector
    }

    static ChildFieldFactory createChildFieldFactory(Injector injector) {
        injector.createChildInjector(new ChildModule()).getInstance(ChildFieldFactory)
    }

    static CheckBoxFieldFactory createCheckBoxFieldFactory(Injector injector) {
        injector.createChildInjector(new CheckBoxModule()).getInstance(CheckBoxFieldFactory)
    }

    @Before
    void setupBean() {
        this.bean = new ChildBean()
    }
}
