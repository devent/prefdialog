/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.table.TableModel;

/**
 * Table field with a custom model. The field value of the table field is not
 * used and is just a placeholder for the annotation. The model of the table
 * must be set.
 * <p>
 * <h4>Example table model field:</h4>
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;TableField(model = &quot;tableModel&quot;)
 * public Object table;
 * 
 * public MyTableModel tableModel;
 * </pre>
 * 
 * <h4>Example table model class:</h4>
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;TableField(modelClass = MyTableModel.class)
 * public Object table;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface Table {

    /**
     * The name of the field name to use for the custom {@link TableModel}.
     * Defaults to an empty name which means no field is set.
     */
    String model() default "";

    /**
     * The custom {@link TableModel} to use with this table field. The model
     * must have the default constructor available for instantiation.
     */
    Class<? extends TableModel>[] modelClass() default {};

    /**
     * The names of the field names to use for the custom
     * {@link TypedTableCellRenderer} default cell renderers. The table cell
     * renderers are set for the returned cell type.
     * 
     * @see DefaultTypedTableCellRenderer
     */
    String[] renderers() default {};

    /**
     * The custom {@link TypedTableCellRenderer} renderers to use with this
     * table field. The table cell renderers are set for the returned cell type.
     * The renderers must have the default constructor available for
     * instantiation.
     * 
     * @see DefaultTypedTableCellRenderer
     */
    Class<? extends TypedTableCellRenderer>[] rendererClasses() default {};

    /**
     * The names of the field names to use for the custom
     * {@link TypedTableCellEditor} default cell editors. The table cell editors
     * are set for the returned cell type.
     * 
     * @see DefaultTypedTableCellEditor
     */
    String[] editors() default {};

    /**
     * The custom {@link TypedTableCellEditor} editors to use with this table
     * field. The table cell editors are set for the returned cell type. The
     * editors must have the default constructor available for instantiation.
     * 
     * @see DefaultTypedTableCellEditor
     */
    Class<? extends TypedTableCellEditor>[] editorClasses() default {};

}
