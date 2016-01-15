/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field to show and select the font. Shows a panel to select the font. The
 * initial value can be set and is used as the initial selected font.
 * <p>
 * Example:
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FontChooser
 * private Font font;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface FontChooser {

	/**
	 * The minimum font chooser panel height. Default is 480 pixels.
	 */
	int minimumHeight() default 480;
}
