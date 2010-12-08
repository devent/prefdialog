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
package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.Group;

public class GroupFieldHandler extends AbstractChildFieldHandler<GroupPanel> {

	public GroupFieldHandler(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Group.class, new GroupPanel());
	}

	@Override
	public void applyInput(Object parent) {
		parent = getComponentValue();
		super.applyInput(parent);
	}
}
