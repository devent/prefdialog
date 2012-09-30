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

import java.awt.event.KeyEvent

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
		def field = factory.create(container, bean, ARRAY_ELEMENTS_FIELD)
						.createField()
		def comboBox
		beginPanelFrame title, container, {
			comboBox = fixture.comboBox(ARRAY_ELEMENTS)
		}, {
			comboBox.requireSelection("One")
			comboBox.selectItem(1)
			comboBox.requireSelection("Two")
			comboBox.selectItem(2)
			comboBox.requireSelection("Three")
		}
	}

	@Test
	void "array elements with null value apply input"() {
		def field = factory.create(container, bean, ARRAY_ELEMENTS_FIELD)
						.createField()
		def comboBox
		beginPanelFrame title, container, {
			comboBox = fixture.comboBox(ARRAY_ELEMENTS)
		}, {
			field.applyInput()
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "One"
		}, {
			comboBox.selectItem(1)
			field.applyInput()
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "Two"
		}, {
			comboBox.selectItem(2)
			field.applyInput()
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "Three"
		}
	}

	@Test
	void "array elements with null value restore input"() {
		def field = factory.create(container, bean, ARRAY_ELEMENTS_FIELD)
						.createField()
		def comboBox
		beginPanelFrame title, container, {
			comboBox = fixture.comboBox(ARRAY_ELEMENTS)
		}, {
			field.applyInput()
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "One"
		}, {
			comboBox.selectItem(1)
			field.restoreInput()
			comboBox.requireSelection("One")
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "One"
		}, {
			comboBox.selectItem(2)
			field.restoreInput()
			comboBox.requireSelection("One")
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS}" == "One"
		}
	}

	@Test
	void "array elements with second value selected"() {
		factory.create(container, bean, ARRAY_ELEMENTS_SECOND_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(ARRAY_ELEMENTS_SECOND)
		}, {
			field.requireSelection("Two")
			field.selectItem(0)
			field.requireSelection("One")
			field.selectItem(2)
			field.requireSelection("Three")
		}
	}

	@Test
	void "array elements with second value selected restore input"() {
		def field = factory.create(container, bean, ARRAY_ELEMENTS_SECOND_FIELD).
						createField()
		def comboBox
		beginPanelFrame title, container, {
			comboBox = fixture.comboBox(ARRAY_ELEMENTS_SECOND)
		}, {
			comboBox.requireSelection("Two")
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS_SECOND}" == "Two"
		}, {
			comboBox.selectItem(0)
			field.restoreInput()
			comboBox.requireSelection("Two")
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS_SECOND}" == "Two"
		}, {
			comboBox.selectItem(2)
			field.restoreInput()
			comboBox.requireSelection("Two")
			assert bean."${ComboBoxBean.ARRAY_ELEMENTS_SECOND}" == "Two"
		}
	}

	@Test
	void "list elements with null value"() {
		factory.create(container, bean, LIST_ELEMENTS_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(LIST_ELEMENTS)
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
		factory.create(container, bean, CUSTOM_MODEL_FIELD_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_MODEL_FIELD)
		}, {
			field.requireSelection("Eins")
			field.selectItem(1)
			field.requireSelection("Zwei")
			field.selectItem(2)
			field.requireSelection("Drei")
		}
	}

	@Test
	void "custom model field null"() {
		factory.create(container, bean, CUSTOM_MODEL_FIELD_NULL_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_MODEL_FIELD_NULL)
			assert bean.modelFieldNull != null
		}, {
			field.requireSelection("Eins")
			field.selectItem(1)
			field.requireSelection("Zwei")
			field.selectItem(2)
			field.requireSelection("Drei")
		}
	}

	@Test
	void "custom renderer field"() {
		factory.create(container, bean, CUSTOM_RENDERER_FIELD_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_RENDERER_FIELD)
		}, {
			field.requireSelection("ONE")
			field.selectItem(1)
			field.requireSelection("TWO")
			field.selectItem(2)
			field.requireSelection("THREE")
		}
	}

	@Test
	void "custom renderer field null"() {
		factory.create(container, bean, CUSTOM_RENDERER_FIELD_NULL_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_RENDERER_FIELD_NULL)
			assert bean.rendererFieldNull != null
		}, {
			field.requireSelection("ONE")
			field.selectItem(1)
			field.requireSelection("TWO")
			field.selectItem(2)
			field.requireSelection("THREE")
		}
	}

	@Test
	void "custom model class"() {
		factory.create(container, bean, CUSTOM_MODEL_CLASS_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_MODEL_CLASS)
		}, {
			field.requireSelection("Eins")
			field.selectItem(1)
			field.requireSelection("Zwei")
			field.selectItem(2)
			field.requireSelection("Drei")
		}
	}

	@Test
	void "custom renderer class"() {
		factory.create(container, bean, CUSTOM_RENDERER_CLASS_FIELD).createField()
		def field
		beginPanelFrame title, container, {
			field = fixture.comboBox(CUSTOM_RENDERER_CLASS)
		}, {
			field.requireSelection("ONE")
			field.selectItem(1)
			field.requireSelection("TWO")
			field.selectItem(2)
			field.requireSelection("THREE")
		}
	}

	@Test
	void "editable combo box apply input"() {
		def field = factory.create(container, bean, EDITABLE_FIELD).createField()
		def comboBox
		def text = "Zwei"
		beginPanelFrame title, container, {
			comboBox = fixture.comboBox(EDITABLE)
		}, {
			comboBox.enterText text
			comboBox.pressAndReleaseKeys KeyEvent.VK_ENTER
			field.applyInput()
			comboBox.requireSelection(text)
			assert bean."${ComboBoxBean.EDITABLE}" == text
		}
	}
}
