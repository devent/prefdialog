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

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Field to show and select a file. Opens a dialog to select the file. The
 * initial value can be set and is used as the initial selected file. The model
 * must be set and is used to open the file chooser dialog so that the user can
 * select the file.
 * <p>
 * Example:
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FileChooser(modelClass = OpenDialogModel.class)
 * public File file = new File(&quot;.&quot;);
 * </pre>
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FileChooser(model = &quot;dialogModel&quot;)
 * public File file = new File(&quot;.&quot;);
 * 
 * public FileChooserModel dialogModel = new OpenDialogModel();
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface FileChooser {

	/**
	 * The text of the button that opens the file chooser dialog. Default is the
	 * unicode text "Open…".
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 */
	String buttonText() default "Open…";

	/**
	 * Sets the mnemonic character with an optimal mnemonic index for the button
	 * that opens the file chooser dialog. The string can contain a key code
	 * name or the character. The mnemonic can also be a resource name that is
	 * queried in the supplied texts resource. Defaults to character "o" for
	 * "Open". Examples:
	 * <ul>
	 * <li>{@code VK_A}</li>
	 * <li>{@code a}</li>
	 * <li>{@code VK_A,5}</li>
	 * <li>{@code a,5}</li>
	 * </ul>
	 * 
	 * @since 3.0
	 */
	String buttonMnemonic() default "o";

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
	 * "file_chooser_open_folder" image resource.
	 */
	String buttonIcon() default "file_chooser_open_folder";

	/**
	 * The size of the button icon that opens the file chooser dialog. Defaults
	 * to {@link IconSize#SMALL}.
	 * 
	 * @see IconSize
	 */
	IconSize buttonIconSize() default IconSize.SMALL;

	/**
	 * The name of the field name to use for the custom {@link FileChooserModel}
	 * . Defaults to an empty name which means no field is set. Either the model
	 * field or the model class must be set.
	 * 
	 * @since 3.0
	 */
	String model() default "";

	/**
	 * The custom {@link FileChooserModel} to use with this file chooser field.
	 * The model must have the default constructor available for instantiation.
	 * Either the model field or the model class must be set. Although the
	 * attribute is an array only the first element is used.
	 * 
	 * @since 3.0
	 */
	Class<? extends FileChooserModel>[] modelClass() default {};
}
