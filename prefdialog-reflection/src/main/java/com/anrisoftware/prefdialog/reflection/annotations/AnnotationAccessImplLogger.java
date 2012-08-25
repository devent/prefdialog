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
package com.anrisoftware.prefdialog.reflection.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link AnnotationAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class AnnotationAccessImplLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link AnnotationAccessImpl}.
	 */
	AnnotationAccessImplLogger() {
		super(AnnotationAccessImpl.class);
	}

	ReflectionError noSuchMethodError(NoSuchMethodException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		ReflectionError ex = new ReflectionError(
				"No such element found in the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError illegalAccessError(IllegalAccessException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		ReflectionError ex = new ReflectionError(
				"Illegal access to the element in the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError invocationTargetError(InvocationTargetException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		ReflectionError ex = new ReflectionError(
				"Exception thrown in the element of the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field);
		log.error(ex.getLocalizedMessage());
		return ex;
	}
}
