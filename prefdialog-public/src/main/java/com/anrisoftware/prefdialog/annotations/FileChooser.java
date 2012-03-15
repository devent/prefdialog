/*
 * Copyright 2010-2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 * Annotation to create a {@link JTextField} and {@link JButton} for this field.
 * If the user click on the button a {@link JFileChooser} will open for the user
 * to select a file. The text field will show the selected path of the file.
 * 
 * The value can be <code>null</code> and if not <code>null</code> the value is
 * the selected file in the {@link JFileChooser}.
 * 
 * Example:
 * 
 * <pre>
 * &#064;FileChooser
 * private File file = new File(&quot;&quot;);
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface FileChooser {

	/**
	 * The width of the file chooser inside the container. Default value is
	 * -1.0. Valid width can be a double value or the special values of:
	 * <dl>
	 * <dt>-1.0</dt>
	 * <dd>fill the available space;</dd>
	 * <dt>-2.0</dt>
	 * <dd>enough space to accommodate the preferred size of the field;</dd>
	 * <dt>-3.0</dt>
	 * <dd>allocated just enough space to accommodate the minimum size of the
	 * field.</dd>
	 * </dl>
	 */
	double width() default -1.0;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 * Default value is <code>false</code>.
	 */
	boolean readonly() default false;

	/**
	 * The title of the file chooser. The title is shown above of the file
	 * chooser field and should be a description. If left empty, the title is
	 * the same as the name of the field in the class. Default is the empty
	 * string <code>""</code>.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not. Default value is
	 * <code>true</code>.
	 */
	boolean showTitle() default true;

	/**
	 * The {@link TextPosition} of the file chooser field title. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The {@link IconSize} of the file chooser field title icon. Default is
	 * {@link IconSize.SMALL}.
	 */
	IconSize iconSize() default IconSize.SMALL;

	/**
	 * The icon for the field title, should be a resource name. The resource
	 * name needs to have the place holder %d for the icon size. There must be
	 * one file for each of the used icon sizes. The icon sizes are 16, 22, 32
	 * and 48. Default is empty.
	 */
	String icon() default "";

	/**
	 * The file chooser button text, default is the unicode text "Open…".
	 */
	String buttonText() default "Open…";

	/**
	 * The {@link TextPosition} of the file chooser button. Default is
	 * {@link TextPosition#TEXT_ALONGSIDE_ICON}.
	 */
	TextPosition buttonTextPosition() default TextPosition.TEXT_ALONGSIDE_ICON;

	/**
	 * The {@link IconSize} of the file chooser button icon. Default is
	 * {@link IconSize.SMALL}.
	 */
	IconSize buttonIconSize() default IconSize.SMALL;

	/**
	 * The icon for the button, should be a resource name. The resource name
	 * needs to have the place holder %d for the icon size. There must be one
	 * file for each of the used icon sizes. The icon sizes are 16, 22, 32 and
	 * 48. Default is
	 * "com/anrisoftware/prefdialog/panel/inputfields/filechooser/oxygen/document-open-folder-%d.png"
	 * .
	 */
	String buttonIcon() default "com/anrisoftware/prefdialog/panel/inputfields/filechooser/oxygen/document-open-folder-%d.png";

}
