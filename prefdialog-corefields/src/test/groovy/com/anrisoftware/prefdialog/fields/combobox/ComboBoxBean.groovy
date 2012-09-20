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

import javax.swing.ComboBoxModel
import javax.swing.ListCellRenderer

import org.apache.commons.lang3.reflect.FieldUtils

import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with combo box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ComboBoxBean {

	static final ARRAY_ELEMENTS = "arrayElementsBoxNullValue"

	static final ARRAY_ELEMENTS_FIELD = FieldUtils.getField(ComboBoxBean, ARRAY_ELEMENTS, true)

	static final ARRAY_ELEMENTS_SECOND = "arrayElementsBoxValueSecond"

	static final ARRAY_ELEMENTS_SECOND_FIELD = FieldUtils.getField(ComboBoxBean, ARRAY_ELEMENTS_SECOND, true)

	static final LIST_ELEMENTS = "listElementsBoxNullValue"

	static final LIST_ELEMENTS_FIELD = FieldUtils.getField(ComboBoxBean, LIST_ELEMENTS, true)

	static final CUSTOM_MODEL_FIELD = "customModelFieldNullValue"

	static final CUSTOM_MODEL_FIELD_FIELD = FieldUtils.getField(ComboBoxBean, CUSTOM_MODEL_FIELD, true)

	static final CUSTOM_MODEL_FIELD_NULL = "customModelFieldNull"

	static final CUSTOM_MODEL_FIELD_NULL_FIELD = FieldUtils.getField(ComboBoxBean, CUSTOM_MODEL_FIELD_NULL, true)

	static final CUSTOM_RENDERER_FIELD = "customRendererField"

	static final CUSTOM_RENDERER_FIELD_FIELD = FieldUtils.getField(ComboBoxBean, CUSTOM_RENDERER_FIELD, true)

	static final CUSTOM_RENDERER_FIELD_NULL = "customRendererFieldNull"

	static final CUSTOM_RENDERER_FIELD_NULL_FIELD = FieldUtils.getField(ComboBoxBean, CUSTOM_RENDERER_FIELD_NULL, true)

	@FieldComponent
	@ComboBox(elements = "arrayElements")
	String arrayElementsBoxNullValue

	@FieldComponent
	@ComboBox(elements = "arrayElements")
	String arrayElementsBoxValueSecond = "Two"

	String[] arrayElements = ["One", "Two", "Three"]

	@FieldComponent
	@ComboBox(elements = "listElements")
	String listElementsBoxNullValue

	List listElements = ["One", "Two", "Three"]

	@FieldComponent
	@ComboBox(model = "modelField")
	String customModelFieldNullValue

	ComboBoxModel modelField = new CustomComboBoxModel()

	@FieldComponent
	@ComboBox(model = "modelFieldNull")
	String customModelFieldNull

	CustomComboBoxModel modelFieldNull

	@FieldComponent
	@ComboBox(elements = "listElements", renderer = "rendererField")
	String customRendererField

	ListCellRenderer rendererField = new CustomComboBoxRenderer()

	@FieldComponent
	@ComboBox(elements = "listElements", renderer = "rendererFieldNull")
	String customRendererFieldNull

	CustomComboBoxRenderer rendererFieldNull
}

