/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-api.
 *
 * prefdialog-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.Action;

/**
 * <p>
 * Field with a group of buttons. The value must be set to an array or an
 * {@link Iterable} that contains the {@link Action} for each button.
 * </p>
 * Examples: a) array of actions field
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ButtonGroup
 * public Action[] buttons = { ... };
 * </pre>
 * 
 * b) collection of actions field
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ButtonGroup
 * public Iterable&lt;Action&gt; buttons = ...;
 * </pre>
 * 
 * c) array of actions method
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ButtonGroup
 * public Action[] getButtons() {
 *   return new Action[] { ... };
 * }
 * </pre>
 * 
 * d) collection of actions method
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ButtonGroup
 * public Iterable&lt;Action&gt; getButtons() {
 * 	return list;
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface ButtonGroup {

	/**
	 * The horizontal alignment of the buttons. Defaults to
	 * {@link HorizontalAlignment#RIGHT}.
	 * 
	 * @see HorizontalAlignment
	 */
	HorizontalAlignment horizontalAlignment() default HorizontalAlignment.RIGHT;

}
