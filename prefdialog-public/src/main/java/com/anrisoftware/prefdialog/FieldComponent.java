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
package com.anrisoftware.prefdialog;

import java.awt.Component;

import com.anrisoftware.prefdialog.annotations.TextField;

/**
 * The base interface for all Swing components that are used for the preferences
 * fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface FieldComponent {

	/**
	 * Sets the width of the Swing component inside the preferences container.
	 */
	void setWidth(double width);

	/**
	 * Sets the name of the Swing component. The name should be a unique ID of
	 * the component inside the preferences container.
	 */
	void setName(String name);

	/**
	 * Sets the title of the Swing component. The title is visible to the user.
	 */
	void setTitle(String title);

	/**
	 * Sets the value of the Swing component. The value can be a text as for
	 * {@link TextField} or anything else. The value is taken from the
	 * preferences bean object.
	 */
	void setValue(Object value);

	/**
	 * Returns the value of the Swing component. The value can be a new text
	 * from a {@link TextField} or anything else. The returned value is set as
	 * the new value in the preferences bean object.
	 */
	Object getValue();

	/**
	 * Sets if the component is enabled or not. The component is usually
	 * disabled for read-only fields.
	 */
	void setEnabled(boolean enabled);

	/**
	 * Tests if the current input is valid.
	 * 
	 * @return <code>true</code> if the current input is valid or
	 *         <code>false</code> if it is not valid.
	 */
	boolean isInputValid();

	/**
	 * Returns the Swing {@link Component} to be added in the preferences
	 * container.
	 */
	Component getAWTComponent();

}
