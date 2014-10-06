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
package com.anrisoftware.prefdialog.fields;

import java.awt.Component;

/**
 * Factory to create a new field component.
 * 
 * @param <ComponentType>
 *            the {@link Component} for this field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldFactory<ComponentType extends Component> {

	/**
	 * Creates a new field component.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param fieldName
	 *            the name of the field in the parent object.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<ComponentType> create(Object parentObject, String fieldName);

}
