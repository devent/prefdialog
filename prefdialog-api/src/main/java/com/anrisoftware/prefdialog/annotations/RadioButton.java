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

import java.awt.event.ActionListener;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * Field for a radio button. Radio buttons are usually in a group so the user
 * can chose one option for the group. For this single radio button an action
 * can be assigned that is called when the user clicks on the button.
 * <p>
 * <h2>Examples</h2>
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;RadioButton
 * public boolean optionFoo;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface RadioButton {

	/**
	 * The text of the check-box. Defaults to the empty string which means the
	 * field name is used as the text.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @see Texts
	 * 
	 * @since 3.0
	 */
	String text() default "";

	/**
	 * Sets if the text of the check-box should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 * 
	 * @since 3.0
	 */
	boolean showText() default true;

	/**
	 * The name of the field name to use for the custom button {@link Action} or
	 * {@link ActionListener}. Defaults to an empty name which means no field is
	 * set.
	 * 
	 * @since 3.0
	 */
	String action() default "";

	/**
	 * Sets the {@link Action} or {@link ActionListener} class for the button.
	 * The custom action must have a public standard constructor available for
	 * instantiation.
	 * 
	 * @see AbstractButton#addActionListener(ActionListener)
	 * @see AbstractButton#setAction(Action)
	 * 
	 * @since 3.0
	 */
	Class<? extends ActionListener>[] actionClass() default {};

	/**
	 * The name of the field name to use for the button group
	 * {@link ButtonGroup} to put the radio buttons in a shared group. Defaults
	 * to an empty name which means no field is set.
	 * 
	 * @since 3.0
	 */
	String group() default "";

}
