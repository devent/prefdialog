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
package com.anrisoftware.prefdialog.fields.colorbutton

import java.awt.Color

import org.apache.commons.lang3.reflect.FieldUtils

import com.anrisoftware.prefdialog.annotations.ColorButton
import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment

/**
 * Bean with color button fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ColorButtonBean {

	static final COLOR_NULL_VALUE = "colorNullValue"

	static final COLOR_NULL_VALUE_FIELD = FieldUtils.getField(ColorButtonBean, COLOR_NULL_VALUE, true)

	static final COLOR_BLACK = "colorBlack"

	static final COLOR_BLACK_FIELD = FieldUtils.getField(ColorButtonBean, COLOR_BLACK, true)

	static final COLOR_RIGHT = "colorRight"

	static final COLOR_RIGHT_FIELD = FieldUtils.getField(ColorButtonBean, COLOR_RIGHT, true)

	static final COLOR_MIDDLE = "colorMiddle"

	static final COLOR_MIDDLE_FIELD = FieldUtils.getField(ColorButtonBean, COLOR_MIDDLE, true)

	static final COLOR_LEFT = "colorLeft"

	static final COLOR_LEFT_FIELD = FieldUtils.getField(ColorButtonBean, COLOR_LEFT, true)

	@FieldComponent
	@ColorButton
	Color colorNullValue

	@FieldComponent
	@ColorButton
	Color colorBlack = Color.BLACK

	@FieldComponent
	@ColorButton(horizontalAlignment = HorizontalAlignment.RIGHT)
	Color colorRight = Color.BLACK

	@FieldComponent
	@ColorButton(horizontalAlignment = HorizontalAlignment.MIDDLE)
	Color colorMiddle = Color.BLACK

	@FieldComponent
	@ColorButton(horizontalAlignment = HorizontalAlignment.LEFT)
	Color colorLeft = Color.BLACK
}

