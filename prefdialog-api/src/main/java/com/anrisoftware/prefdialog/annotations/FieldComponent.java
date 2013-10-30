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

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Field component. All fields for which a field component should be created
 * must be annotated with this annotation. Defines attributes that are common to
 * all fields.
 * <p>
 * 
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
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Documented
public @interface FieldComponent {

	/**
	 * The title of the field. The title is shown above of the group and should
	 * contain a description. Defaults to empty string.
	 * <p>
	 * The title can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @see Texts
	 */
	String title() default "";

	/**
	 * If the title of the field should be visible or not. Defaults to
	 * {@code true}.
	 */
	boolean showTitle() default true;

	/**
	 * Sets the mnemonic character with an optimal mnemonic index. The string
	 * can contain a key code name or the character. Defaults to empty string.
	 * Examples:
	 * <ul>
	 * <li>{@code VK_A}</li>
	 * <li>{@code a}</li>
	 * <li>{@code VK_A,5}</li>
	 * <li>{@code a,5}</li>
	 * </ul>
	 * <p>
	 * The mnemonic can also be a resource name that is queried in the supplied
	 * texts resource. Example:
	 * <ul>
	 * <li>{@code field_mnemonic}</li>
	 * </ul>
	 * <p>
	 * 
	 * @since 3.0
	 */
	String mnemonic() default "";

	/**
	 * The tool-tip text for the field. Set to empty string to disable the
	 * tool-tip. Defaults to empty string. The tool-tip can also be a resource
	 * name that is queried in the supplied texts resource.
	 */
	String toolTip() default "";

	/**
	 * Set the text that is shown if the user input is invalid. Defaults to
	 * empty string.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @since 3.0
	 */
	String invalidText() default "";

	/**
	 * If this field should be read-only. If read-only is set then the user can
	 * not use buttons in the group. Defaults to {@code false}.
	 */
	boolean readOnly() default false;

	/**
	 * The width of the field inside the container. The width can be in pixels,
	 * a percentage or some special constant. Default is set to the TableLayout
	 * preferred size constant.
	 */
	double width() default -2.0;

	/**
	 * The height of the field inside the container. The height can be in
	 * pixels, a percentage or some special constant. Default is set to the
	 * TableLayout preferred size constant.
	 * 
	 * @since 3.0
	 */
	double height() default -2.0;

	/**
	 * The position of the title of the field. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 * 
	 * @see TextPosition
	 */
	TextPosition titlePosition() default TextPosition.TEXT_ONLY;

	/**
	 * The resource name of the icon or empty string if no icon should be set.
	 * The resource name is queried in the supplied images resource. Default to
	 * empty string.
	 * 
	 * @see Images
	 */
	String icon() default "";

	/**
	 * The size of the icon. Defaults to {@link IconSize#SMALL}.
	 * 
	 * @see IconSize
	 */
	IconSize iconSize() default IconSize.SMALL;

	/**
	 * The locale for the field. Defaults to the empty locale name which means
	 * the default locale is used.
	 */
	String locale() default "";

	/**
	 * Ordering number of the field. The fields are sorted based on the order
	 * number in ascending order. Multiple fields can have the same order number
	 * but then the order is unspecified.
	 * 
	 * @since 3.0
	 */
	int order() default 0;
}
