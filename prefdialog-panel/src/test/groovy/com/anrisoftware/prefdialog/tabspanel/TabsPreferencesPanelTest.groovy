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
package com.anrisoftware.prefdialog.tabspanel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.tabspanel.TabsPreferencesPanelBean.*

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
 * @see TabsPreferencesPanelField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelTest {

	@Test
	void "panel with child"() {
		def title = "$NAME::panel with child"
		def fieldName = NULL_VALUE
		def field = factory.create(bean, fieldName)
		field.createPanel(injector)
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

	@Test
	void "renderer field"() {
		def title = "$NAME::renderer field"
		def fieldName = RENDERER_FIELD
		def field = factory.create(bean, fieldName)
		field.createPanel(injector)
		def container = field.getAWTComponent()
		def tabbedPane

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			assert bean.customRenderer != null
			tabbedPane = fixture.tabbedPane fieldName
			tabbedPane.target.getTitleAt(0) == "Custom A"
			tabbedPane.target.getTitleAt(1) == "Custom B"
		}, { FrameFixture fixture ->
			tabbedPane.selectTab(1)
			assert field.getValue() == bean.childB
			tabbedPane.selectTab(0)
			assert field.getValue() == bean.childA
		})
	}

	@Test
	void "renderer class field"() {
		def title = "$NAME::renderer class field"
		def fieldName = RENDERER_CLASS_FIELD
		def field = factory.create(bean, fieldName)
		field.createPanel(injector)
		def container = field.getAWTComponent()
		def tabbedPane

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			tabbedPane = fixture.tabbedPane fieldName
			tabbedPane.target.getTitleAt(0) == "Custom A"
			tabbedPane.target.getTitleAt(1) == "Custom B"
		}, { FrameFixture fixture ->
			tabbedPane.selectTab(1)
			assert field.getValue() == bean.childB
			tabbedPane.selectTab(0)
			assert field.getValue() == bean.childA
		})
	}

	//@Test
	void "manually"() {
		def title = "$NAME::manually"
		def fieldName = NULL_VALUE
		def field = factory.create(bean, fieldName)
		field.createPanel(injector)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container).withFixture({
			Thread.sleep 60 * 1000l
			assert false : "manually test"
		})
	}

	static Injector injector

	static TabsPreferencesPanelFieldFactory factory

	static final String NAME = TabsPreferencesPanelTest.class.simpleName

	TabsPreferencesPanelBean bean

	@BeforeClass
	static void setupFactories() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(new CoreFieldComponentModule())
		factory = injector.createChildInjector(
				new TabsPreferencesPanelModule()).getInstance(TabsPreferencesPanelFieldFactory)
	}

	@Before
	void setupBean() {
		bean = new TabsPreferencesPanelBean()
	}
}
