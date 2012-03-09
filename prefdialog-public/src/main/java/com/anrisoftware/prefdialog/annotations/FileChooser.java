/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
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
	 * The width of the file chooser inside the container.
	 */
	double width() default -1.0;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	/**
	 * The title of the combo box. The title is shown above of the check box and
	 * should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not.
	 */
	boolean showTitle() default true;
}
