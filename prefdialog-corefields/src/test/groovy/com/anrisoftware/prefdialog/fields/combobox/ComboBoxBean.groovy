/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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

import com.anrisoftware.prefdialog.annotations.ComboBox
import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * Bean with combo box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ComboBoxBean {

	static final ARRAY_ELEMENTS = "arrayElementsBoxNullValue"

	static final ARRAY_ELEMENTS_SECOND = "arrayElementsBoxValueSecond"

	static final LIST_ELEMENTS = "listElementsBoxNullValue"

	static final CUSTOM_MODEL_FIELD = "customModelFieldNullValue"

	static final CUSTOM_MODEL_FIELD_NULL = "customModelFieldNull"

	static final CUSTOM_RENDERER_FIELD = "customRendererField"

	static final CUSTOM_RENDERER_FIELD_NULL = "customRendererFieldNull"

	static final CUSTOM_MODEL_CLASS = "customModelClass"

	static final CUSTOM_RENDERER_CLASS = "customRendererClass"

	static final EDITABLE = "editable"

	@FieldComponent
	@ComboBox(elements = "arrayElements")
	public String arrayElementsBoxNullValue

	@FieldComponent
	@ComboBox(elements = "arrayElements")
	public String arrayElementsBoxValueSecond = "Two"

	public String[] arrayElements = ["One", "Two", "Three"]

	@FieldComponent
	@ComboBox(elements = "listElements")
	public String listElementsBoxNullValue

	public List listElements = ["One", "Two", "Three"]

	@FieldComponent
	@ComboBox(model = "modelField")
	public String customModelFieldNullValue

	public ComboBoxModel modelField = new CustomComboBoxModel()

	@FieldComponent
	@ComboBox(model = "modelFieldNull")
	public String customModelFieldNull

	public CustomComboBoxModel modelFieldNull

	@FieldComponent
	@ComboBox(elements = "listElements", renderer = "rendererField")
	public String customRendererField

	public ListCellRenderer rendererField = new CustomComboBoxRenderer()

	@FieldComponent
	@ComboBox(elements = "listElements", renderer = "rendererFieldNull")
	public String customRendererFieldNull

	public CustomComboBoxRenderer rendererFieldNull

	@FieldComponent
	@ComboBox(modelClass = CustomComboBoxModel.class)
	public String customModelClass

	@FieldComponent
	@ComboBox(elements = "listElements", rendererClass = CustomComboBoxRenderer.class)
	public String customRendererClass

	@FieldComponent
	@ComboBox(model = "modelField", editable = true)
	public String editable
}

