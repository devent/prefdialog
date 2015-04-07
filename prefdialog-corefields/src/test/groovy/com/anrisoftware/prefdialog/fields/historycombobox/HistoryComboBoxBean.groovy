/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.historycombobox

import com.anrisoftware.prefdialog.annotations.FieldComponent

/**
 * @see HistoryComboBox
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class HistoryComboBoxBean {

	static final LIST_ELEMENTS = "listElementsBoxNullValue"

	static final LIST_ELEMENTS_DEFAULT_ITEMS = "listElementsDefaultItems"

	static final LIST_ELEMENTS_HISTORY = "listElementsHistory"

	static final LIST_ELEMENTS_MAXIMUM = "listElementsMaximum"

	@FieldComponent
	@HistoryComboBox(elements = "listElements", editable = true)
	public String listElementsBoxNullValue

	@FieldComponent
	@HistoryComboBox(elements = "listElements", editable = true, defaultItems = "defaultItems")
	public String listElementsDefaultItems

	@FieldComponent
	@HistoryComboBox(elements = "listElements", editable = true, defaultItems = "defaultItems", history = "history")
	public String listElementsHistory

	@FieldComponent
	@HistoryComboBox(elements = "listElements", editable = true, maximumHistory = 2)
	public String listElementsMaximum

	public List listElements = ["One", "Two", "Three"]

	public List defaultItems = ["Default A", "Default B"]

	public List history = []
}

