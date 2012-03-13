/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.child;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.FieldComponent;
import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * A child component is a {@link FieldComponent} that contains other fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public abstract class ChildComponent extends AbstractLabelFieldPanel<JPanel> {

	/**
	 * Set the field as the panel.
	 * 
	 * @param panel
	 *            the {@link JPanel} that contains the child fields.
	 */
	protected ChildComponent(JPanel panel) {
		super(panel);
	}

	/**
	 * Adds a new field {@link FieldHandler} to the child panel.
	 */
	public abstract void addField(FieldHandler<?> inputField);

}
