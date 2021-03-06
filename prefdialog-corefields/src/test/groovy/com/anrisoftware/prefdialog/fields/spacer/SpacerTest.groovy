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
package com.anrisoftware.prefdialog.fields.spacer

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.fields.spacer.SpacerBean.*
import static com.anrisoftware.prefdialog.fields.spacer.SpacerFixture.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.fields.child.ChildFieldFactory
import com.anrisoftware.prefdialog.fields.child.ChildModule
import com.anrisoftware.prefdialog.fields.textfield.TextFieldFactory
import com.anrisoftware.prefdialog.fields.textfield.TextFieldModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpacerField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpacerTest {

    @Test
    void "default spacer"() {
        def title = "$NAME::default spacer"
        def fieldName = SPACER
        def childField = childFactory.create(bean, CHILD_BEAN)
        def childBean = bean.childBeanWithDefaultSpacer
        def container = childField.getAWTComponent()
        childField.addField textFactory.create(childBean, TOP)
        childField.addField spacerFactory.create(childBean, fieldName)
        childField.addField textFactory.create(childBean, BOTTOM)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            def spacer = spacerFixture(fixture, fieldName)
            assert spacer.target.visible
        })
    }

    @Test
    void "fixed spacer"() {
        def title = "$NAME::fixed spacer"
        def fieldName = SPACER
        def childField = childFactory.create(bean, CHILD_BEAN_FIXED_SPACER)
        def childBean = bean.childBeanWithFixedSpacer
        def container = childField.getAWTComponent()
        childField.addField textFactory.create(childBean, TOP)
        childField.addField spacerFactory.create(childBean, fieldName)
        childField.addField textFactory.create(childBean, BOTTOM)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            def spacer = spacerFixture(fixture, fieldName)
            assert spacer.target.visible
            assert spacer.target.height == 100
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = SPACER
        def childField = childFactory.create(bean, CHILD_BEAN)
        def container = childField.getAWTComponent()
        def childBean = bean.childBeanWithDefaultSpacer
        childField.addField textFactory.create(childBean, TOP)
        childField.addField spacerFactory.create(childBean, fieldName)
        childField.addField textFactory.create(childBean, BOTTOM)

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            Thread.sleep 60 * 1000l
            assert false : "Deactivate manually test"
        })
    }

    static final String NAME = SpacerTest.class.simpleName

    static Injector injector

    static SpacerFieldFactory spacerFactory

    static childFactory

    static textFactory

    SpacerBean bean

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = Guice.createInjector(new CoreFieldComponentModule())
        this.spacerFactory = createSpacerFieldFactory injector
        this.childFactory = createChildFieldFactory injector
        this.textFactory = createTextFieldFactory injector
    }

    @Before
    void setupBean() {
        this.bean = new SpacerBean()
    }

    static ChildFieldFactory createChildFieldFactory(Injector injector) {
        injector.createChildInjector(new ChildModule()).getInstance(
                ChildFieldFactory)
    }

    static TextFieldFactory createTextFieldFactory(Injector injector) {
        injector.createChildInjector(new TextFieldModule()).getInstance(
                TextFieldFactory)
    }

    static SpacerFieldFactory createSpacerFieldFactory(Injector injector) {
        injector.createChildInjector(new SpacerModule()).getInstance(
                SpacerFieldFactory)
    }
}
