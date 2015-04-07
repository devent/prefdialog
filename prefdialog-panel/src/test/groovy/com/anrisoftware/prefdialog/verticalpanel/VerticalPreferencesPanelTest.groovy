/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelBean.*

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
 * @see VerticalPreferencesPanelField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class VerticalPreferencesPanelTest {

	@Test
	void "children"() {
		def title = "$NAME::children"
		def fieldName = NULL_VALUE
		def field = factory.create(bean, fieldName)
		field.createPanel(injector)
		def container = field.getAWTComponent()
		def panel

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			panel = fixture.panel fieldName
			panel.requireVisible()
			assert field.getValue() == bean.childA
		}, { FrameFixture fixture ->
			fixture.panel "childA" requireVisible()
			fixture.panel "childB" requireVisible()
		})
	}

	@Test
	void "ordered children"() {
		def title = "$NAME::ordered children"
		def fieldName = NULL_VALUE
		def field = factory.create(orderBean, fieldName)
		field.createPanel(injector)
		def container = field.getAWTComponent()
		def panel

		new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
			panel = fixture.panel fieldName
			panel.requireVisible()
			assert field.getValue() == orderBean.childA
		}, { FrameFixture fixture ->
			fixture.panel "childA" requireVisible()
			fixture.panel "childB" requireVisible()
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

	static VerticalPreferencesPanelFieldFactory factory

	static final String NAME = VerticalPreferencesPanelTest.class.simpleName

	VerticalPreferencesPanelBean bean

	VerticalPreferencesPanelOrderBean orderBean

	@BeforeClass
	static void setupFactories() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(new CoreFieldComponentModule())
		factory = injector.createChildInjector(
				new VerticalPreferencesPanelModule()).getInstance(VerticalPreferencesPanelFieldFactory)
	}

	@Before
	void setupBean() {
		bean = new VerticalPreferencesPanelBean()
		orderBean = new VerticalPreferencesPanelOrderBean()
	}
}
