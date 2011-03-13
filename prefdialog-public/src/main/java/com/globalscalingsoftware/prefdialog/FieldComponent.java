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
package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

public interface FieldComponent {

	void setWidth(double width);

	void setName(String name);

	void setTitle(String title);

	void setValue(Object value);

	Object getValue();

	Component getAWTComponent();

	void setEnabled(boolean enabled);

	/**
	 * Tests if the current input is valid.
	 * 
	 * @return <code>true</code> if the current input is valid or
	 *         <code>false</code> if it is not valid.
	 */
	boolean isInputValid();

	/**
	 * Set the {@link InputChangedCallback} that will be called if the input of
	 * this field changes.
	 */
	void setInputChangedCallback(InputChangedCallback callback);

}
