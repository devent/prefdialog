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
package com.globalscalingsoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Callback that is called if a {@link Annotation} is found.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface AnnotationDiscoveryCallback {

	/**
	 * Called if an {@link Annotation} was found in the field.
	 * 
	 * @param field
	 *            the {@link Field} where the {@link Annotation} was found.
	 * 
	 * @param value
	 *            the {@link Object value} of the field.
	 * 
	 * @param a
	 *            the {@link Annotation} that was found.
	 */
	void fieldAnnotationDiscovered(Field field, Object value, Annotation a);

}
