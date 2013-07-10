/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

/**
 * Field to let the user choose from multiple values in a combo-box.
 * <p>
 * Either a list of values can be set from a different field or a custom
 * {@link ComboBoxModel} can be used. It is also possible to set a custom
 * {@link ListCellRenderer}. The initial value can be set and is used as the
 * initial value of the field.
 * <p>
 * Examples: a) sets the field with a list of the values
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(elements = &quot;someFieldElements&quot;)
 * public String someField;
 * // as array
 * public String[] someFieldElements = { ... };
 * // or as list
 * public List&lt;String&gt; someFieldElements = { ... };
 * </pre>
 * 
 * b) sets the field with an instance of the custom model. If no instance is
 * set, the model must have a public standard constructor available for
 * instantiation. The new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(model = &quot;customModel&quot;)
 * public String someField;
 * 
 * public ComboBoxModel customModel;
 * </pre>
 * 
 * c) sets the custom model class. The custom model must have a public standard
 * constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(modelClass = CustomComboBoxModel.class)
 * public String someField;
 * </pre>
 * 
 * d) sets the field with an instance of the custom renderer. If no instance is
 * set, the renderer must have a public standard constructor available for
 * instantiation. The new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(renderer = &quot;customRenderer&quot;)
 * public String someField;
 * 
 * public ListCellRenderer customRenderer;
 * </pre>
 * 
 * e) sets the custom renderer class. The custom renderer must have a public
 * standard constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(rendererClass = CustomListCellRenderer.class)
 * public String someField;
 * </pre>
 * 
 * f) make the combo box editable.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ComboBox(model = &quot;customModel&quot;, editable = true)
 * public String someField;
 * 
 * public ComboBoxModel customModel = new CustomComboBoxModel();
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface ComboBox {

	/**
	 * The name of the field name to use for the elements of the combo box. The
	 * can be an array or an {@link Iterable}. Not needed if the model or model
	 * class is set with {@link #model()} or {@link #modelClass()}. Defaults to
	 * an empty name which means no field is set.
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
	 * The custom {@link ComboBoxModel} to use with this combo box. The model
	 * must have the default constructor available for instantiation.
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ComboBoxModel>[] modelClass() default {};

	/**
	 * The name of the field name to use for the custom {@link ListCellRenderer}
	 * . Defaults to an empty name which means no field is set.
	 */
	String renderer() default "";

	/**
	 * The custom {@link ListCellRenderer} to use with this combo box. The
	 * renderer must have the default constructor available for instantiation.
	 */
	Class<? extends ListCellRenderer<?>>[] rendererClass() default {};

	/**
	 * The name of the field name to use for the custom {@link ComboBoxEditor}.
	 * Defaults to an empty name which means no field is set.
	 * 
	 * @since 3.0
	 */
	String editor() default "";

	/**
	 * The custom {@link ComboBoxEditor} to use with this combo box. The editor
	 * must have the default constructor available for instantiation.
	 * 
	 * @since 3.0
	 */
	Class<? extends ComboBoxEditor>[] editorClass() default {};
}
