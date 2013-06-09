/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.tabspanel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.tabspanel.TabsPreferencesPanelBean.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test the {@link PreferencesPanel}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelTest {

	@Test
	void "panel with child"() {
		def title = "$NAME :: panel with child"
		def fieldName = NULL_VALUE
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		def tabbedPane

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			tabbedPane = fixture.tabbedPane fieldName
			tabbedPane.requireVisible()
			assert field.getValue() == bean.childA
		}, { FrameFixture fixture ->
			tabbedPane.selectTab(1)
			assert field.getValue() == bean.childB
			tabbedPane.selectTab(0)
			assert field.getValue() == bean.childA
		})
	}

	//@Test
	void "manually"() {
		def title = "$NAME :: manually"
		def fieldName = CHILD
		def field = factory.create(bean, fieldName)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({
			Thread.sleep 60 * 1000l
			assert false : "Deactivate manually test"
		})
	}

	static Injector injector

	static TabsPreferencesPanelFieldFactory factory

	static final String NAME = TabsPreferencesPanelTest.class.simpleName

	TabsPreferencesPanelBean bean

	@BeforeClass
	static void setupFactories() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(
				new AnnotationsModule(), new BeansModule(), new TabsPreferencesPanelModule())
		factory = injector.getInstance TabsPreferencesPanelFieldFactory
	}

	@Before
	void setupBean() {
		bean = new TabsPreferencesPanelBean()
	}
}
