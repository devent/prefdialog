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

import org.apache.commons.lang3.reflect.FieldUtils
import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.google.inject.Injector

class ButtonGroupTest extends FieldTestUtils {

	static final title = "Button Group Test"
	
	static final buttons = "buttons"
	
	static final buttonsField = FieldUtils.getField(Bean, buttons, true)

	static class Bean {

		def button1Callback
		
		def button2Callback
		
		@FieldComponent
		@ButtonGroup
		def buttons = [
			new ButtonAction("Button 1", button1Callback),
			new ButtonAction("Button 2", button2Callback)
		]
	}
	
	ButtonGroupFieldFactory factory
	
	Bean bean
	
	boolean button1Called
	
	boolean button2Called

	@Before
	void beforeTest() {
		super.beforeTest()
		factory = injector.getInstance ButtonGroupFieldFactory
		bean = new Bean()
		button1Called = false
		button2Called = false
		bean.button1Callback = { button1Called = true }
		bean.button2Callback = { button2Called = true }
	}
	
	Injector createInjector() {
		def parent = super.createInjector()
		parent.createChildInjector new ButtonGroupModule()
	}
	
	@Test
	void "click on buttons"() {
		def container = new JPanel()
		def field = factory.create(container, this, buttonsField)
		beginPanelFrame title, container, {
			fixture.button("button-0-$buttons").click()
			assert button1Called
		}, {
			fixture.button("button-1-$buttons").click()
			assert button2Called
		}
	}
}
