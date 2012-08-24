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
 * Groups fields together logically. The group field contains child fields and
 * are added to this group. If the value is not set then the group field is
 * instantiated; in that case the group needs to have the standard contructor
 * available.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Group {

	/**
	 * The title of the group field. The title is shown above of the field and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the group field should be visible or not. Defaults to
	 * {@code true}.
	 */
	boolean showTitle() default true;

	/**
	 * If this group field should be read-only. If read-only is set then all the
	 * child fields are read-only as well. Defaults to {@code false}.
	 */
	boolean readonly() default false;

	/**
	 * The width of the group field inside the container.
	 */
	double width() default -1.0;

	/**
	 * The position of the title of the group field. Default is
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
