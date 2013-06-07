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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Groups fields together logically. The group field contains child fields and
 * are added to this group. If the value is not set then the group field is
 * instantiated; in that case the group needs to have the standard constructor
 * available.
 * <p>
 * Example:
 * 
 * <pre>
 * class GroupFoo
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	private String textFoo;
 * 
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	private String textBar;
 * }
 * 
 * class Preferences {
 * 
 * 	&#064;FieldComponent
 * 	&#064;Group
 * 	private GroupFoo groupFoo
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface Group {
}
