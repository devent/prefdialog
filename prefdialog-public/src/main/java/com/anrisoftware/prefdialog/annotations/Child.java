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

/**
 * <p>
 * Annotate a new child panel in the preferences dialog.
 * </p>
 * 
 * The child panel will be shown left in the tree list. If the user clicks on
 * the item of the tree list the preferences of this child will be shown at the
 * right side of the dialog. Example:
 * 
 * <pre>
 * &#064;Child
 * private ChildPreferences childPreferences;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Child {

	/**
	 * The title of the child. If left empty the title will be field name.
	 * Default is the empty string "".
	 */
	String title() default "";

	boolean showTitle() default true;

	/**
	 * The width of the child inside the container. Default value is -1.0. Valid
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

}
