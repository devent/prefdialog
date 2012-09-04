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

import com.anrisoftware.resources.api.Texts;

/**
 * Check box field. A check box field can only be checked or unchecked. The
 * value of field must be set.
 * <p>
 * Example with text set to the field name.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Checkbox
 * private boolean isImportant;
 * </pre>
 * 
 * Example with text set to the field name but is not visible.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Checkbox(showText = false)
 * private boolean isImportant;
 * </pre>
 * 
 * Example with text set.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Checkbox(text = &quot;Is Important&quot;)
 * private boolean isImportant;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Checkbox {

	/**
	 * The text of the check-box. Defaults to the empty string which means the
	 * field name is used as the text.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @see Texts
	 */
	String text() default "";

	/**
	 * Sets if the text of the check-box should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 */
	boolean showText() default true;
}
