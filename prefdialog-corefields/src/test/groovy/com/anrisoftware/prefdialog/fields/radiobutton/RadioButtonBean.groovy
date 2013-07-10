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
package com.anrisoftware.prefdialog.fields.radiobutton

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.ButtonGroup

import com.anrisoftware.prefdialog.annotations.FieldButton
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

	static final GROUP_MEMBER_A = "groupMemberA"

	static final GROUP_MEMBER_B = "groupMemberB"

	@FieldComponent
	@FieldButton
	@RadioButton
	public Boolean nullValue

	@FieldComponent
	@FieldButton
	@RadioButton
	public boolean noText

	@FieldComponent
	@FieldButton(text = "Radio Button Text")
	@RadioButton
	public boolean withText

	@FieldComponent(title = "radiobutton_with_text_resource_title")
	@FieldButton(text = "radiobutton_with_text_resource_text")
	@RadioButton
	public boolean withTextResource

	@FieldComponent
	@FieldButton(showText = false)
	@RadioButton
	public boolean notShowText

	@FieldComponent(readOnly = true)
	@FieldButton
	@RadioButton
	public boolean readOnly

	@FieldComponent
	@FieldButton(action = "actionListener")
	@RadioButton
	public boolean withActionListener

	@FieldComponent
	@FieldButton(action = "action")
	@RadioButton
	public boolean withAction

	@FieldComponent
	@FieldButton(actionClass = CustomActionListener)
	@RadioButton
	public boolean withActionListenerClass

	@FieldComponent
	@FieldButton(actionClass = CustomAction)
	@RadioButton
	public boolean withActionClass

	@FieldComponent
	@FieldButton(group = "buttonGroup")
	@RadioButton
	public boolean groupMemberA

	@FieldComponent
	@FieldButton(group = "buttonGroup")
	@RadioButton
	public boolean groupMemberB

	public ButtonGroup buttonGroup

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
		this.buttonGroup = new ButtonGroup()
	}
}

