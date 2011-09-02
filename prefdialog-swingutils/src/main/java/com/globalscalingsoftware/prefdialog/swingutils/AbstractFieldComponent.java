/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.swingutils;

import java.awt.Component;

import javax.swing.JComponent;

import com.globalscalingsoftware.prefdialog.FieldComponent;

/**
 * Implements a {@link FieldComponent} that captures a generic
 * {@link JComponent} field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public abstract class AbstractFieldComponent<FieldType extends JComponent>
		implements FieldComponent {

	private final FieldType field;

	public AbstractFieldComponent(FieldType field) {
		this.field = field;
	}

	/**
	 * Returns the {@link FieldType} that we are capturing.
	 */
	public FieldType getField() {
		return field;
	}

	@Override
	public Component getAWTComponent() {
		return field;
	}

	@Override
	public void setName(String name) {
		field.setName(name);
	}

	@Override
	public void setWidth(double width) {
	}

	@Override
	public void setEnabled(boolean enabled) {
		field.setEnabled(enabled);
	}

}
