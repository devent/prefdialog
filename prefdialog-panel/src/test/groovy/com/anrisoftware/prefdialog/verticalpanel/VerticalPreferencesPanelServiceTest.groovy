/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.verticalpanel

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelBean.*
import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelService.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Injector

/**
 * @see VerticalPreferencesPanelService
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class VerticalPreferencesPanelServiceTest {

    @Test
    void "service"() {
        def title = "$NAME::service"
        def fieldName = NULL_VALUE
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({})
    }

    static final String NAME = VerticalPreferencesPanelServiceTest.class.simpleName

    static Injector injector

    static VerticalPreferencesPanelFieldFactory factory

    VerticalPreferencesPanelBean bean

    @BeforeClass
    static void setupFactories() {
        injector = createInjector()
        factory = findService(INFO).getFactory(injector)
    }

    @Before
    void setupBean() {
        bean = new VerticalPreferencesPanelBean()
    }
}
