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

/**
 * Panel that contains other fields. The child field needs either to the default
 * constructor available or be set to an instance.
 * <p>
 * Example:
 * 
 * <pre>
 * // is instantiated by the default constructor
 * &#064;FieldComponent
 * &#064;Child
 * private ChildPreferences childPreferences;
 * 
 * // is manually instantiated
 * &#064;FieldComponent
 * &#064;Child
 * private ChildPreferences childPreferences = new ...;
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Child {
}
