package com.anrisoftware.prefdialog.fields.buttongroup

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment

/**
 * Button group with default attributes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
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

