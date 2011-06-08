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
package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JRadioButton;

/**
 * Annotation to create a group of {@link JRadioButton} for a field.
 * 
 * Example:
 * 
 * <pre>
 * enum Colors {
 *     BLACK, BLUE, RED
 * }
 * 
 * ...
 * 
 * &#064;RadioButton
 * private Colors colors;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * 
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface RadioButton {

	/**
	 * The width of the radio buttons group inside the container.
	 */
	double width() default -1.0;

	/**
	 * If this input fields should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	/**
	 * The title of the radio buttons group. The title is shown above of the
	 * group and should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not.
	 */
	boolean showTitle() default true;

	/**
	 * How many columns should the group have. The radio buttons are distributed
	 * evenly in the columns.
	 */
	int columns() default 1;
}
