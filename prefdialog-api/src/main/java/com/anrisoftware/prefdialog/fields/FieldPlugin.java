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
package com.anrisoftware.prefdialog.fields;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Returns the field component for the specified field of the preferences bean
 * object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface FieldPlugin {

	/**
	 * Returns the annotation type that this field is for.
	 * 
	 * @return the annotation {@link Class} type.
	 */
	Class<? extends Annotation> getFieldAnnotation();

	/**
	 * Returns the field component for the specified field of the preferences
	 * bean object.
	 * 
	 * @param injector
	 *            the parent injector containing the bound dependencies.
	 * 
	 * @param component
	 *            the {@link Component} that the user can enter input data for
	 *            the field.
	 * 
	 * @param bean
	 *            the preferences bean object.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @param <K>
	 *            the type of the component, must be of type {@link Component}.
	 * 
	 * @param <T>
	 *            the type of the field, must be of type {@link FieldComponent}.
	 * 
	 * @return the {@link FieldComponent}.
	 * 
	 * @throws FieldPluginError
	 *             if an unexpected exception is thrown while returning the
	 *             field component.
	 */
	<K extends Component, T extends FieldComponent<K>> T getField(
			Object injector, Component component, Object bean, Field field);
}
