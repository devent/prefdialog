/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.listbox

import javax.swing.ListCellRenderer
import javax.swing.ListModel

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.ListBox

/**
 * Bean with list box fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ListBoxBean {

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
    @ListBox(elements = "arrayElements")
    public String arrayElementsBoxNullValue

    @FieldComponent
    @ListBox(elements = "arrayElements")
    public String arrayElementsBoxValueSecond = "Two"

    public String[] arrayElements = ["One", "Two", "Three"]

    @FieldComponent
    @ListBox(elements = "listElements")
    public String listElementsBoxNullValue

    public List listElements = ["One", "Two", "Three"]

    @FieldComponent
    @ListBox(model = "modelField")
    public String customModelFieldNullValue

    public ListModel modelField = new CustomListModel()

    @FieldComponent
    @ListBox(model = "modelFieldNull")
    public String customModelFieldNull

    public CustomListModel modelFieldNull

    @FieldComponent
    @ListBox(elements = "listElements", renderer = "rendererField")
    public String customRendererField

    public ListCellRenderer rendererField = new CustomCellRenderer()

    @FieldComponent
    @ListBox(elements = "listElements", renderer = "rendererFieldNull")
    public String customRendererFieldNull

    public CustomCellRenderer rendererFieldNull

    @FieldComponent
    @ListBox(modelClass = CustomListModel.class)
    public String customModelClass

    @FieldComponent
    @ListBox(elements = "listElements", rendererClass = CustomCellRenderer.class)
    public String customRendererClass
}
