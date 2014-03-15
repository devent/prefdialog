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

import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 * Field to let the user choose from one or multiple values in a list-box.
 * <p>
 * If the list box field is of type array or list then the default list box
 * selection mode is set to multiple interval; for any other type the default
 * list box selection mode is set to single selection. The initial value can be
 * set and is used as the initial selected value or values of the list box. The
 * list box values can be set from either a different field or a custom list
 * model can be used. It is also possible to set a custom cell renderer.
 * <p>
 * Examples: a) sets the field with a list of the values
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ListBox(elements = &quot;someFieldElements&quot;)
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
 * &#064;ListBox(model = &quot;customModel&quot;)
 * public String someField;
 * 
 * public ListModel customModel;
 * </pre>
 * 
 * c) sets the custom model class. The custom model must have a public standard
 * constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ListBox(modelClass = CustomComboBoxModel.class)
 * public String someField;
 * </pre>
 * 
 * d) sets the field with an instance of the custom renderer. If no instance is
 * set, the renderer must have a public standard constructor available for
 * instantiation. The new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ListBox(renderer = &quot;customRenderer&quot;)
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
 * &#064;ListBox(rendererClass = CustomListCellRenderer.class)
 * public String someField;
 * </pre>
 * 
 * f) make the combo box editable.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;ListBox(model = &quot;customModel&quot;, editable = true)
 * public String someField;
 * 
 * public ListModel customModel = new CustomListModel();
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface ListBox {

	/**
	 * The name of the field name to use for the elements of the list box. The
	 * can be an array or an {@link Iterable}. Not needed if the model or model
	 * class is set with {@link #model()} or {@link #modelClass()}. Defaults to
	 * an empty name which means no field is set.
	 */
	String elements() default "";

	/**
	 * The name of the field name to use for the custom {@link ListModel}.
	 * Defaults to an empty name which means no field is set.
	 */
	String model() default "";

	/**
	 * The custom {@link ListModel} to use with this list box. The model must
	 * have the default constructor available for instantiation.
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ListModel>[] modelClass() default {};

	/**
	 * The name of the field name to use for the custom {@link ListCellRenderer}
	 * . Defaults to an empty name which means no field is set.
	 */
	String renderer() default "";

	/**
	 * The custom {@link ListCellRenderer} to use with this list box. The
	 * renderer must have the default constructor available for instantiation.
	 */
	Class<? extends ListCellRenderer<?>>[] rendererClass() default {};
}
