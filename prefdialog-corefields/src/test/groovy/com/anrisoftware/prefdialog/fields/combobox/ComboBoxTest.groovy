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
package com.anrisoftware.prefdialog.fields.combobox

import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupPluginModule.*
import static com.anrisoftware.prefdialog.fields.combobox.ComboBoxBean.*

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.core.FieldTestUtils
import com.google.inject.Injector

/**
 * Test the {@link ComboBoxField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ComboBoxTest extends FieldTestUtils {

	static final title = "Combo Box Test"

	ComboBoxFieldFactory factory

	ComboBoxBean bean

	JPanel container

	@Before
	void createFactory() {
		factory = injector.getInstance ComboBoxFieldFactory
		bean = new ComboBoxBean()
		container = new JPanel()
	}

	Injector createInjector() {
		super.createInjector().createChildInjector new ComboBoxModule()
	}

	@Test
	void "array elements with null value"() {
		factory.create(container, bean, ARRAY_ELEMENTS_BOX_NULL_VALUE_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(ARRAY_ELEMENTS_BOX_NULL_VALUE)
		}, {
			field.requireSelection("One")
			field.selectItem(1)
			field.requireSelection("Two")
			field.selectItem(2)
			field.requireSelection("Three")
		}
	}

	@Test
	void "array elements with second value selected"() {
		factory.create(container, bean, ARRAY_ELEMENTS_BOX_VALUE_SECOND_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(ARRAY_ELEMENTS_BOX_VALUE_SECOND)
		}, {
			field.requireSelection("Two")
			field.selectItem(0)
			field.requireSelection("One")
			field.selectItem(2)
			field.requireSelection("Three")
		}
	}

	@Test
	void "list elements with null value"() {
		factory.create(container, bean, LIST_ELEMENTS_BOX_NULL_VALUE_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(LIST_ELEMENTS_BOX_NULL_VALUE)
		}, {
			field.requireSelection("One")
			field.selectItem(1)
			field.requireSelection("Two")
			field.selectItem(2)
			field.requireSelection("Three")
		}
	}

	@Test
	void "custom model field"() {
		factory.create(container, bean, CUSTOM_MODEL_FIELD_NULL_VALUE_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_MODEL_FIELD_NULL_VALUE)
		}, {
			field.requireSelection("Eins")
			field.selectItem(1)
			field.requireSelection("Zwei")
			field.selectItem(2)
			field.requireSelection("Drei")
		}
	}
}
