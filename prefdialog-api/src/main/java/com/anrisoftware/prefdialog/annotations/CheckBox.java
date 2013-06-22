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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Check box field. A check box field can only be checked or unchecked. The
 * value of field must be set.
 * <p>
 * 
 * <h2>Examples</h2>
 * 
 * Simple example.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FieldButton
 * &#064;ComboBox
 * public boolean buttonField;
 * </pre>
 * 
 * With getter and setter.
 * 
 * <pre>
 * private boolean important;
 * 
 * public void setImportant(boolean important) {
 * 	this.important = important;
 * }
 * 
 * &#064;FieldComponent
 * &#064;FieldButton
 * &#064;ComboBox
 * public boolean isImportant() {
 * 	return important;
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
public @interface CheckBox {

}
