/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Panel that contains other fields. The child field needs either to the default
 * constructor available or be set to an instance.
 * <p>
 * Example:
 * 
 * <pre>
 * // is instantiated by the default constructor
 * &#064;Child
 * private ChildPreferences childPreferences;
 * 
 * // is manually instantiated
 * &#064;Child
 * private ChildPreferences childPreferences = new ...;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Child {

	/**
	 * The title of the child field. The title is shown above of the field and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the child field should be visible or not. Defaults to
	 * {@code true}.
	 */
	boolean showTitle() default true;

	/**
	 * If this child field should be read-only. If read-only is set then the
	 * user can not modify the value of the child field. Defaults to
	 * {@code false}.
	 */
	boolean readonly() default false;

	/**
	 * The width of the child field inside the container.
	 */
	double width() default -1.0;

	/**
	 * The position of the title of the child field. Default is
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
}
