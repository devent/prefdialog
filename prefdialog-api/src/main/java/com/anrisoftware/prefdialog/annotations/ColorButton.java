/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-public.
 *
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field to let the user specify a color. The initial value of the field must be
 * set and is the initial selected color.
 * <p>
 * Example:
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ColorButton
 * private Color color = Color.BLACK;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ColorButton {

	/**
	 * The horizontal alignment of the button. Defaults to
	 * {@link HorizontalAlignment#RIGHT}.
	 * 
	 * @see HorizontalAlignment
	 */
	HorizontalAlignment horizontalAlignment() default HorizontalAlignment.RIGHT;

}
