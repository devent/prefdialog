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

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

/**
 * Field to input formatted text. The initial value can be set and is used as
 * the initial text of the field.
 * <p>
 * Examples:
 *
 * <pre>
 * &#064;FieldComponent
 * &#064;FormattedTextField
 * public String textField;
 * 
 * &#064;FieldComponent
 * &#064;FormattedTextField
 * public int intField;
 * </pre>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface FormattedTextField {

    /**
     * If the field should be editable. Set to {@code true} if the text field
     * should be editable or {@code false} if not. Defaults to {@code true}.
     */
    boolean editable() default true;

    /**
     * The name of the field name to use for the custom
     * {@link AbstractFormatterFactory}. Defaults to an empty name which means
     * no field is set.
     *
     * @since 3.5
     */
    String formatterFactory() default "";

    /**
     * Sets the {@link AbstractFormatterFactory} class. The custom formatter
     * factory must have a public standard constructor available for
     * instantiation.
     *
     * @see JFormattedTextField#setFormatterFactory(javax.swing.JFormattedTextField.AbstractFormatterFactory)
     *
     * @since 3.5
     */
    Class<? extends AbstractFormatterFactory>[] formatterFactoryClass() default {};
}
