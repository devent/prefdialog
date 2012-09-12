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
package com.anrisoftware.prefdialog.fields.colorbutton;

import static org.apache.commons.lang3.Validate.isInstanceOf;

import java.awt.Color;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;

/**
 * Logging messages for {@link ColorButtonField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ColorButtonFieldLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link ColorButtonField}.
	 */
	ColorButtonFieldLogger() {
		super(ColorButtonField.class);
	}

	void horizontalAlignmentSet(ColorButtonField field,
			HorizontalAlignment alignment) {
		log.trace("Set the horizontal alignment to {} in the button group {}.",
				alignment, field);
	}

	void checkValueIsColor(ColorButtonField field, Object newValue) {
		isInstanceOf(
				Color.class,
				newValue,
				"The value %s must be of type %s in the color button field %s.",
				newValue, Color.class, field);
	}
}
