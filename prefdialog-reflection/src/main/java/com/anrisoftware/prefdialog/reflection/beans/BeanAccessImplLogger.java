/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link BeanAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class BeanAccessImplLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link BeanAccessImpl}.
	 */
	BeanAccessImplLogger() {
		super(BeanAccessImpl.class);
	}

	ReflectionError illegalAccessError(IllegalAccessException e,
			Object parentObject, String name) {
		ReflectionError ex = new ReflectionError(
				"Illegal access to the field getter", e).addContextValue(
				"parent object", parentObject).addContextValue("name", name);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError illegalAccessError(IllegalAccessException e, Field field,
			Object parentObject) {
		ReflectionError ex = new ReflectionError("Illegal access to the field",
				e).addContextValue("parent object", parentObject)
				.addContextValue("field", field);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError illegalArgumentError(IllegalArgumentException e,
			Object parentObject, String name) {
		ReflectionError ex = new ReflectionError(
				"Illegal argument to the field getter", e).addContextValue(
				"parent object", parentObject).addContextValue("name", name);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError invocationTargetError(InvocationTargetException e,
			Object parentObject, String name) {
		ReflectionError ex = new ReflectionError(
				"Exception thrown in the element of the annotation", e)
				.addContextValue("parent object", parentObject)
				.addContextValue("name", name);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

}
