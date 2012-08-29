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
package com.anrisoftware.prefdialog.fields.buttongroup

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.google.inject.Injector

class ButtonGroupTest extends FieldTestUtils {

	static final title = "Button Group Test"

	ButtonGroupFieldFactory factory

	ButtonGroupBean bean

	@Before
	void beforeTest() {
		super.beforeTest()
		factory = injector.getInstance ButtonGroupFieldFactory
	}

	Injector createInjector() {
		def parent = super.createInjector()
		parent.createChildInjector new ButtonGroupModule()
	}

	@Test
	void "button group field with horizontal alignment right"() {
		def bean = new ButtonGroupBean()
		def field = ButtonGroupBean.BUTTONS_FIELD
		def fieldName = ButtonGroupBean.BUTTONS
		def container = new JPanel()
		factory.create(container, bean, field).createField()
		beginPanelFrame title, container, {
			fixture.button("$fieldName-0-button").click()
			assert bean.button1Called
		}, {
			fixture.button("$fieldName-1-button").click()
			assert bean.button2Called
		}
	}

	@Test
	void "button group field with horizontal alignment middle"() {
		def bean = new ButtonGroupAlignmentMiddleBean()
		def field = ButtonGroupAlignmentMiddleBean.BUTTONS_FIELD
		def fieldName = ButtonGroupAlignmentMiddleBean.BUTTONS
		def container = new JPanel()
		factory.create(container, bean, field).createField()
		beginPanelFrame title, container, {
			fixture.button("$fieldName-0-button").click()
			assert bean.button1Called
		}, {
			fixture.button("$fieldName-1-button").click()
			assert bean.button2Called
		}
	}

	@Test
	void "button group field with horizontal alignment left"() {
		def bean = new ButtonGroupAlignmentLeftBean()
		def field = ButtonGroupAlignmentLeftBean.BUTTONS_FIELD
		def fieldName = ButtonGroupAlignmentLeftBean.BUTTONS
		def container = new JPanel()
		factory.create(container, bean, field).createField()
		beginPanelFrame title, container, {
			fixture.button("$fieldName-0-button").click()
			assert bean.button1Called
		}, {
			fixture.button("$fieldName-1-button").click()
			assert bean.button2Called
		}
	}
}
