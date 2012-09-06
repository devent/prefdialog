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
package com.anrisoftware.prefdialog.fields.child

import static com.anrisoftware.prefdialog.fields.child.ChildBean.*
import static com.anrisoftware.prefdialog.fields.child.ChildPluginModule.*

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.anrisoftware.prefdialog.fields.checkbox.CheckboxFieldFactory
import com.google.inject.Injector

/**
 * Test the {@link ChildField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildTest extends FieldTestUtils {

	static final title = "Child Panel Test"

	ChildFieldFactory factory

	CheckboxFieldFactory checkboxFactory

	ChildBean bean

	JPanel container

	@Before
	void beforeTest() {
		super.beforeTest()
		factory = injector.getInstance ChildFieldFactory
		bean = new ChildBean()
		container = new JPanel()
	}

	Injector createInjector() {
		def parent = super.createInjector()
		parent.createChildInjector new ChildModule()
	}

	@Test
	void "child null value"() {
		factory.create(container, bean, CHILD_NULL_VALUE_FIELD).createField()
		beginPanelFrame title, container, {
			fixture.panel("$CHILD_NULL_VALUE").requireVisible()
		}
	}

	@Test
	void "child null value with added field"() {
		factory.create(container, bean, CHILD_NULL_VALUE_FIELD).createField()
		beginPanelFrame title, container, {
			fixture.panel("$CHILD_NULL_VALUE").requireVisible()
			fixture.scrollPane("$CHILD_NULL_VALUE-$CHILDREN_SCROLL_NAME").requireVisible()
		}
	}
}
