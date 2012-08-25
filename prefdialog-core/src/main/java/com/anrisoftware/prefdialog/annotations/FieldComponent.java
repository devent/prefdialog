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

import com.anrisoftware.resources.api.Images;
import com.anrisoftware.resources.api.Texts;

/**
 * Field component. All fields for which a field component should be created
 * must be annotated with this annotation.
 * <p>
 * Example:
 * 
 * <pre>
 * &#064;FieldComponent(title = "Title", icon = "icon")
 * &#064;TextField
 * private String textField = ...;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface FieldComponent {

	/**
	 * The title of the field. The title is shown above of the group and should
	 * contain a description.
	 * <p>
	 * The title can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @see Texts
	 */
	String title() default "";

	/**
	 * If the title of the field should be visible or not. Defaults to
	 * {@code false}.
	 */
	boolean showTitle() default false;

	/**
	 * The tool-tip text for the field. Defaults to the empty text which means
	 * no tool-tip will be shown.
	 */
	String toolTip() default "";

	/**
	 * If this field should be read-only. If read-only is set then the user can
	 * not use buttons in the group. Defaults to {@code false}.
	 */
	boolean readOnly() default false;

	/**
	 * The width of the field inside the container.
	 */
	double width() default -1.0;

	/**
	 * The position of the title of the field. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 * 
	 * @see TextPosition
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The resource name of icon or empty if no icon should be set. The resource
	 * name is queried in the supplied images resource. Defaults to the empty
	 * resource name which means no icon is set.
	 * 
	 * @see Images
	 */
	String icon() default "";

	/**
	 * The horizontal alignment of the buttons in the group. Defaults to
	 * {@link HorizontalAlignment#RIGHT}.
	 * 
	 * @see HorizontalAlignment
	 */
	HorizontalAlignment horizontalAlignment() default HorizontalAlignment.RIGHT;

	/**
	 * The locale for the field. Defaults to the empty locale name which means
	 * the default locale is used.
	 */
	String locale() default "";
}
