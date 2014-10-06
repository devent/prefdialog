/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.event.ActionListener;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * Field button component. Puts together common attributes for combo-box button
 * and radio button.
 * <p>
 * 
 * <h2>Examples</h2>
 * 
 * Simple example.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FieldButton
 * &#064;ComboBox
 * public boolean buttonField;
 * </pre>
 * 
 * Not visible text.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FieldButton(showText = false)
 * &#064;ComboBox
 * public boolean buttonField;
 * </pre>
 * 
 * Text set.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;FieldButton(text = &quot;Is Important&quot;)
 * &#064;ComboBox
 * public boolean buttonField;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Documented
public @interface FieldButton {

	/**
	 * The text of the button. Defaults to the empty string which means the
	 * field name is used as the text. The text can also be a resource name that
	 * is queried in the supplied texts resource.
	 * 
	 * @see Texts
	 * @see AbstractButton#setText(String)
	 */
	String text() default "";

	/**
	 * Sets if the text of the button should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 */
	boolean showText() default true;

	/**
	 * The name of the field name to use for the custom button {@link Action} or
	 * {@link ActionListener}. Defaults to an empty name which means no field is
	 * set.
	 */
	String action() default "";

	/**
	 * Sets the {@link Action} or {@link ActionListener} class for the button.
	 * The custom action must have a public standard constructor available for
	 * instantiation.
	 * 
	 * @see AbstractButton#addActionListener(ActionListener)
	 * @see AbstractButton#setAction(Action)
	 */
	Class<? extends ActionListener>[] actionClass() default {};

	/**
	 * The name of the field name to use for the button group
	 * {@link ButtonGroup} to put the radio buttons in a shared group. Defaults
	 * to an empty name which means no field is set.
	 */
	String group() default "";

}
