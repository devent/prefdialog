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
 * Field to show and select a file. Opens a dialog to select the file. The
 * initial value can be set and is used as the initial selected file.
 * <p>
 * Example:
 * 
 * <pre>
 * &#064;FileChooser
 * private File file = new File(&quot;.&quot;);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface FileChooser {

	/**
	 * The title of the file field. The title is shown above of the field and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the file field should be visible or not. Defaults to
	 * {@code true}.
	 */
	boolean showTitle() default true;

	/**
	 * If this file field should be read-only. If read-only is set then the user
	 * can not modify the value of the file field. Defaults to {@code false}.
	 */
	boolean readonly() default false;

	/**
	 * The width of the file field inside the container.
	 */
	double width() default -1.0;

	/**
	 * The position of the title of the file field. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 * 
	 * @see TextPosition
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The resource name of icon or empty if no icon should be set. Defaults to
	 * the empty resource name.
	 */
	String icon() default "";

	/**
	 * The text of the button that opens the file chooser dialog. Default is the
	 * unicode text "Open…".
	 */
	String buttonText() default "Open…";

	/**
	 * The position of the title of the button that opens the file chooser
	 * dialog. Default is {@link TextPosition#TEXT_ALONGSIDE_ICON}.
	 * 
	 * @see TextPosition
	 */
	TextPosition buttonTextPosition() default TextPosition.TEXT_ALONGSIDE_ICON;

	/**
	 * The resource name of icon for the button that opens the file chooser
	 * dialog or empty if no icon should be set. Defaults to the
	 * "file_chooser_open_folder" resource.
	 */
	String buttonIcon() default "file_chooser_open_folder";

}
