package com.anrisoftware.prefdialog.fields.buttongroup

import org.apache.commons.lang3.reflect.FieldUtils

import com.anrisoftware.prefdialog.annotations.ButtonGroup
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment

/**
 * Button group with horizontal alignment middle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ButtonGroupAlignmentMiddleBean {

	static final BUTTONS = "buttons"
	
	static final BUTTONS_FIELD = FieldUtils.getField(ButtonGroupAlignmentMiddleBean, BUTTONS, true)

	boolean button1Called

	boolean button2Called

	@FieldComponent
	@ButtonGroup(horizontalAlignment = HorizontalAlignment.MIDDLE)
	def buttons = [
		new ButtonAction("Button 1", { button1Called = true }),
		new ButtonAction("Button 2", { button2Called = true })
	]
		
}
	
