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
 * &#064;ColorButton
 * private Color color = Color.BLACK;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ColorButton {

	/**
	 * The title of the color field. The title is shown above of the field and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the color field should be visible or not. Defaults to
	 * {@code true}.
	 */
	boolean showTitle() default true;

	/**
	 * If this color field should be read-only. If read-only is set then the
	 * user can not modify the value of the color field. Defaults to
	 * {@code false}.
	 */
	boolean readonly() default false;

	/**
	 * The width of the color field inside the container.
	 */
	double width() default -1.0;

	/**
	 * The position of the title of the color field. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 * 
	 * @see TextPosition
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The resource name of icon or empty if no icon should be set. Defaults to
	 * the empty resource name.
	 */
	String icon() default "";

	/**
	 * The horizontal position of the color button to select the color. The
	 * default is {@link HorizontalAlignment#RIGHT}, meaning it will be aligned
	 * to the right side.
	 * 
	 * @see HorizontalAlignment
	 */
	HorizontalAlignment horizontalPosition() default HorizontalAlignment.RIGHT;

}
