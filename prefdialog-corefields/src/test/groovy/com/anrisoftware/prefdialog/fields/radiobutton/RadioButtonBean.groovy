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
package com.anrisoftware.prefdialog.fields.radiobutton

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.AbstractAction
import javax.swing.Action

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.RadioButton

/**
 * @see RadioButton
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class RadioButtonBean {

	static final NULL_VALUE = "nullValue"

	static final NO_TEXT = "noText"

	static final WITH_TEXT = "withText"

	static final WITH_TEXT_RESOURCE = "withTextResource"

	static final NOT_SHOW_TEXT = "notShowText"

	static final READ_ONLY = "readOnly"

	static final WITH_ACTION_LISTENER = "withActionListener"

	static final WITH_ACTION = "withAction"

	static final WITH_ACTION_LISTENER_CLASS = "withActionListenerClass"

	static final WITH_ACTION_CLASS = "withActionClass"

	@FieldComponent
	@RadioButton
	public Boolean nullValue

	@FieldComponent
	@RadioButton
	public boolean noText

	@FieldComponent
	@RadioButton(text = "Radio Button Text")
	public boolean withText

	@FieldComponent(title = "radiobutton_with_text_resource_title")
	@RadioButton(text = "radiobutton_with_text_resource_text")
	public boolean withTextResource

	@FieldComponent
	@RadioButton(showText = false)
	public boolean notShowText

	@FieldComponent(readOnly = true)
	@RadioButton
	public boolean readOnly

	@FieldComponent
	@RadioButton(action = "actionListener")
	public boolean withActionListener

	@FieldComponent
	@RadioButton(action = "action")
	public boolean withAction

	@FieldComponent
	@RadioButton(actionClass = CustomActionListener)
	public boolean withActionListenerClass

	@FieldComponent
	@RadioButton(actionClass = CustomAction)
	public boolean withActionClass

	public ActionListener actionListener

	public Action action

	boolean actionListenerCalled = false

	RadioButtonBean() {
		this.actionListener = { actionListenerCalled = true } as ActionListener
		this.action = new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						actionListenerCalled = true
					}
				}
	}
}

