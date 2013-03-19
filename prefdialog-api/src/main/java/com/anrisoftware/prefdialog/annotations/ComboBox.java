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

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;

/**
 * Field to let the user choose from multiple values in a combo-box.
 * <p>
 * Either a list of values can be set from a different field or a custom
 * {@link ComboBoxModel} can be used. It is also possible to set a custom
 * {@link ListCellRenderer}. The initial value can be set and is used as the
 * initial value of the field.
 * <p>
 * Examples:
 * <p>
 * Sets the field with a list of the values:
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(elements = &quot;someFieldElements&quot;)
 * private String someField;
 * // as array
 * private String[] someFieldElements = { ... };
 * // or as list
 * private List&lt;Object&gt; someFieldElements = { ... };
 * </pre>
 * 
 * Sets the field with an instance of the custom model. If no instance is set,
 * the model must have a public standard constructor available for
 * instantiation. The new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(model = &quot;customModel&quot;)
 * private String someField;
 * private ComboBoxModel customModel = new CustomComboBoxModel();
 * </pre>
 * 
 * Sets the custom model class. The custom model must have a public standard
 * constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(modelClass = CustomComboBoxModel.class)
 * private String someField;
 * </pre>
 * 
 * Sets the field with an instance of the custom renderer. If no instance is
 * set, the renderer must have a public standard constructor available for
 * instantiation. The new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(renderer = &quot;customRenderer&quot;)
 * private String someField;
 * private ListCellRenderer customRenderer = new CustomListCellRenderer();
 * </pre>
 * 
 * Sets the custom renderer class. The custom renderer must have a public
 * standard constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(rendererClass = CustomListCellRenderer.class)
 * private String someField;
 * </pre>
 * 
 * Make the combo box editable.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(model = &quot;customModel&quot;, editable = true)
 * private String someField;
 * private ComboBoxModel customModel = new CustomComboBoxModel();
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ComboBox {

	/**
	 * The name of the field name to use for the elements of the combo box. Not
	 * needed if the model or model class is set with {@link #model()} or
	 * {@link #modelClass()}. Defaults to an empty name which means no field is
	 * set.
	 */
	String elements() default "";

	/**
	 * If the field should be editable. Set to {@code true} if the combo box
	 * should be editable or {@code false} if not. Defaults to {@code false}.
	 */
	boolean editable() default false;

	/**
	 * The name of the field name to use for the custom {@link ComboBoxModel}.
	 * Defaults to an empty name which means no field is set.
	 */
	String model() default "";

	/**
	 * The name of the field name to use for the custom {@link ListCellRenderer}
	 * . Defaults to an empty name which means no field is set.
	 */
	String renderer() default "";

	/**
	 * The custom {@link ComboBoxModel} to use with this combo box. The model
	 * must have the default constructor available for instantiation. Defaults
	 * to the {@link DefaultComboBoxModel}.
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ComboBoxModel> modelClass() default DefaultComboBoxModel.class;

	/**
	 * The custom {@link ListCellRenderer} to use with this combo box. The
	 * renderer must have the default constructor available for instantiation.
	 * Defaults to the {@link DefaultListCellRenderer}.
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ListCellRenderer> rendererClass() default DefaultListCellRenderer.class;
}