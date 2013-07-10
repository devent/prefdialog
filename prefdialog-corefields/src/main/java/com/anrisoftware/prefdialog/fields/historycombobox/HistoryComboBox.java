/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.historycombobox;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;

import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FieldAnnotation;

/**
 * Field to let the select values in a combo-box. The combo box will retain a
 * history of added items in the model.
 * <p>
 * <h2>Examples</h2>
 * 
 * Set the elements from a field.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;HistoryComboBox(elements = &quot;fieldElements&quot;, history = &quot;fieldHistory&quot;, defaultItems = &quot;fieldDefaultItems&quot;)
 * public String field;
 * // as array
 * public String[] fieldElements = new String[] { ... };
 * // or as list
 * public List&lt;String&gt; fieldElements;
 * // the retained history
 * public List&lt;String&gt; fieldHistory;
 * // as array
 * public String[] fieldDefaultItems = new String[] { ... };
 * // or as list
 * public List&lt;String&gt; fieldDefaultItems;
 * </pre>
 * 
 * @see ComboBox
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface HistoryComboBox {

	/**
	 * The name of the field name to use for the elements of the combo box. The
	 * can be an array or an {@link Iterable}. Not needed if the model or model
	 * class is set with {@link #model()} or {@link #modelClass()}. Defaults to
	 * an empty name which means no field is set.
	 */
	String elements() default "";

	/**
	 * The field name of the field to use for the remembered history elements of
	 * the combo box. The field must be {@link Collection}. Defaults to an empty
	 * name which means no field is set.
	 */
	String history() default "";

	/**
	 * The field name of the field to use for the default items for the combo
	 * box. The field can be an array or of type {@link Collection}. The default
	 * items can not be removed from the combo box. Defaults to an empty name
	 * which means no field is set.
	 */
	String defaultItems() default "";

	/**
	 * Sets the maximum entries in the history, excluding the default items. If
	 * the box contains more then the maximum entries then the last entry is
	 * removed. Default to five entries.
	 */
	int maximumHistory() default 5;

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
	 * must have the default constructor available for instantiation. Defaults
	 * to the {@link DefaultComboBoxModel}.
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
	 * Defaults to the {@link DefaultListCellRenderer}.
	 */
	Class<? extends ListCellRenderer<?>>[] rendererClass() default {};

	/**
	 * The name of the field name to use for the custom {@link ComboBoxEditor}.
	 * Defaults to an empty name which means no field is set.
	 */
	String editor() default "";

	/**
	 * The custom {@link ComboBoxEditor} to use with this combo box. The editor
	 * must have the default constructor available for instantiation.
	 */
	Class<? extends ComboBoxEditor>[] editorClass() default {};
}
