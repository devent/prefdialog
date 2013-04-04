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

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Panel that contains other fields. The child field needs either to the default
 * constructor available or be set to an instance.
 * <p>
 * Examples: a) instantiated by the default constructor
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Child
 * public ChildPreferences childPreferences;
 * </pre>
 * 
 * b) manually instantiated
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Child
 * public ChildPreferences childPreferences = new ...;
 * </pre>
 * 
 * c) returned by method
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Child
 * public ChildPreferences getChildPreferences() {
 * }
 * </pre>
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface Child {
}
