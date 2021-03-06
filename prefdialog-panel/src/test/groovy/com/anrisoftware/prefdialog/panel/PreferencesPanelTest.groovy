/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.panel.PreferencesPanelBean.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link PreferencesPanel}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class PreferencesPanelTest {

    @Test
    void "panel with child"() {
        def title = "$NAME::panel with child"
        def fieldName = CHILD
        def field = factory.create(bean, fieldName)
        field.createPanel(injector)
        def container = field.getAWTComponent()

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = CHILD
        def field = factory.create(bean, fieldName)
        field.createPanel(injector)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({
            Thread.sleep 60 * 1000l
            assert false : "manually test"
        })
    }

    static Injector injector

    static PreferencesPanelFieldFactory factory

    static final String NAME = PreferencesPanelTest.class.simpleName

    PreferencesPanelBean bean

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        injector = Guice.createInjector(new CoreFieldComponentModule())
        factory = injector.createChildInjector(
                new PreferencesPanelModule()).getInstance(PreferencesPanelFieldFactory)
    }

    @Before
    void setupBean() {
        bean = new PreferencesPanelBean()
    }
}
