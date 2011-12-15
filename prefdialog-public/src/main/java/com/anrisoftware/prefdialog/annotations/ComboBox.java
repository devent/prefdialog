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

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

/**
 * <p>
 * Annotate a field to be chosen by a {@link JComboBox}.
 * </p>
 * <p>
 * This annotation can be used with another field where the items for the combo
 * box are stored. This field is annotated with the {@link ComboBoxElements}
 * annotation and must have the same value as {@link ComboBox#elements()}.
 * </p>
 * <p>
 * The other possibility is to specify a custom {@link ComboBoxModel} with
 * {@link ComboBox#model()}.
 * </p>
 * <p>
 * Example A:
 * </p>
 * 
 * <pre>
 * &#064;ComboBox(elements = &quot;Some Field&quot;)
 * private String someField;
 * 
 * &#064;ComboBoxElements(&quot;Some Field&quot;)
 * private String[] someField = { &quot;first&quot;, &quot;second&quot;, &quot;third&quot; };
 * </pre>
 * <p>
 * Example B:
 * </p>
 * 
 * <pre>
 * &#064;ComboBox(model = CustomComboBoxModel.class)
 * private String someField;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ComboBox {

	/**
	 * The width of the combo box inside the container.
	 */
	double width() default -1.0;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	/**
	 * The title of the combo box. The title is shown above of the check box and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not.
	 */
	boolean showTitle() default true;

	/**
	 * Optional the name of the field to use for the elements of the combo box.
	 * Needs to be the same as of {@link ComboBoxElements#value()}.
	 */
	String elements() default "";

	/**
	 * Optional a custom {@link ComboBoxModel} to use with this combo box.
	 */
	Class<? extends ComboBoxModel> model() default DefaultComboBoxModel.class;

	/**
	 * Optional a custom {@link ListCellRenderer} to use with this combo box.
	 */
	Class<? extends ListCellRenderer> renderer() default DefaultListCellRenderer.class;
}
