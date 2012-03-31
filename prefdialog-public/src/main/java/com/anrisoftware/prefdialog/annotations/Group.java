/*
 * Copyright 2010-2012 Erwin Müller <erwin.mueller@deventm.org>
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

@Target(FIELD)
@Retention(RUNTIME)
public @interface Group {

	/**
	 * The title of the group. If left empty the title will be the field name.
	 * Default is the empty string "".
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not. Default value is
	 * <code>true</code>.
	 */
	boolean showTitle() default true;

	/**
	 * The width of the group inside the container. Default value is -1.0. Valid
	 * width can be a double value or the special values of:
	 * <dl>
	 * <dt>-1.0</dt>
	 * <dd>fill the available space;</dd>
	 * <dt>-2.0</dt>
	 * <dd>enough space to accommodate the preferred size of the field;</dd>
	 * <dt>-3.0</dt>
	 * <dd>allocated just enough space to accommodate the minimum size of the
	 * field.</dd>
	 * </dl>
	 */
	double width() default -1.0;

	/**
	 * The {@link TextPosition} of the group. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The {@link IconSize} of the group icon. Default is {@link IconSize.SMALL}
	 * .
	 */
	IconSize iconSize() default IconSize.SMALL;

	/**
	 * The icon for the group, should be a resource name. The resource name
	 * needs to have the place holder %d for the icon size. There must be one
	 * file for each of the used icon sizes. The icon sizes are 16, 22, 32 and
	 * 48. Default is empty.
	 */
	String icon() default "";

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;
}
