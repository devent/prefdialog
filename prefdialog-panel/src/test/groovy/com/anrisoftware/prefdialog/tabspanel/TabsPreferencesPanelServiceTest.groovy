/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.tabspanel

import static com.anrisoftware.prefdialog.core.AbstractTitleField.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.tabspanel.TabsPreferencesPanelBean.*
import static com.anrisoftware.prefdialog.tabspanel.TabsPreferencesPanelService.*

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see TabsPreferencesPanelService
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelServiceTest {

	@Test
	void "with defaults"() {
		def title = "$NAME :: with defaults"
		def fieldName = NULL_VALUE
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({})
	}

	static final String NAME = TabsPreferencesPanelServiceTest.class.simpleName

	static TabsPreferencesPanelFieldFactory factory

	TabsPreferencesPanelBean bean

	@BeforeClass
	static void setupFactories() {
		factory = findService(INFO).getFactory()
	}

	@Before
	void setupBean() {
		bean = new TabsPreferencesPanelBean()
	}
}
