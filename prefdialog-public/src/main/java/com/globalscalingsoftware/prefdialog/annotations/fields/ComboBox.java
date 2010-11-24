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
package com.globalscalingsoftware.prefdialog.annotations.fields;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JComboBox;

/**
 * <p>
 * Annotate a field to be chosen by a {@link JComboBox}.
 * </p>
 * <p>
 * This annotation requires another field where the items for the combobox are
 * stored. This field is annotated with the {@link ComboBoxElements} annotation
 * and must have the same {@link ComboBox#value()} value.
 * </p>
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * &#064;ComboBox(&quot;Some Field&quot;)
 * private String someField;
 * 
 * &#064;ComboBoxElements(&quot;Some Field&quot;)
 * private String[] someField = { &quot;first&quot;, &quot;second&quot;, &quot;third&quot; };
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ComboBox {

	/**
	 * The name of the combobox, needs to be the same as of
	 * {@link ComboBoxElements}.
	 */
	String value();

	/**
	 * The width of the combobox inside the container.
	 */
	double width() default -1.0;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;
}
