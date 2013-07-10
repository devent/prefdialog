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
package com.anrisoftware.prefdialog.fields.buttongroup

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment

/**
 * Button group with default attributes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ButtonGroupBean {

	static final BUTTONS = "buttons"

	static final BUTTONS_RIGHT = "buttonsRight"

	static final BUTTONS_LEFT = "buttonsLeft"

	static final BUTTONS_MIDDLE = "buttonsMiddle"

	boolean button1Called

	boolean button2Called

	@FieldComponent
	@ButtonGroup
	public List buttons = [
		new ButtonAction("Button 1", { button1Called = true }),
		new ButtonAction("Button 2", { button2Called = true })
	]

	@FieldComponent
	@ButtonGroup(horizontalAlignment = HorizontalAlignment.RIGHT)
	public List buttonsRight = [
		new ButtonAction("Button 1", { button1Called = true }),
		new ButtonAction("Button 2", { button2Called = true })
	]

	@FieldComponent
	@ButtonGroup(horizontalAlignment = HorizontalAlignment.LEFT)
	public List buttonsLeft = [
		new ButtonAction("Button 1", { button1Called = true }),
		new ButtonAction("Button 2", { button2Called = true })
	]

	@FieldComponent
	@ButtonGroup(horizontalAlignment = HorizontalAlignment.MIDDLE)
	public List buttonsMiddle = [
		new ButtonAction("Button 1", { button1Called = true }),
		new ButtonAction("Button 2", { button2Called = true })
	]
}

