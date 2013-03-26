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

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Logging messages for {@link AnnotationAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@Singleton
class AnnotationAccessImplLogger extends AbstractLogger {

	private final TextResource noSuchMethodError;

	private final TextResource illegalAccessError;

	private final TextResource exceptionThrownError;

	/**
	 * Creates logger for {@link AnnotationAccessImpl}.
	 */
	@Inject
	AnnotationAccessImplLogger(TextsFactory textsFactory) {
		super(AnnotationAccessImpl.class);
		Texts texts = textsFactory.create(getClass().getSimpleName());
		this.noSuchMethodError = texts.getResource("no_such_method_error");
		this.illegalAccessError = texts.getResource("illegal_access_error");
		this.exceptionThrownError = texts.getResource("exception_thrown_error");
	}

	ReflectionError noSuchMethodError(NoSuchMethodException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		return logException(
				new ReflectionError("No such element found", e)
						.addContextValue("name", name)
						.addContextValue("annotation", annotationClass)
						.addContextValue("field", field),
				noSuchMethodError.getText(), name, annotationClass.getName(),
				field);
	}

	ReflectionError illegalAccessError(IllegalAccessException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		return logException(
				new ReflectionError("Illegal access to element", e)
						.addContextValue("name", name)
						.addContextValue("annotation", annotationClass)
						.addContextValue("field", field),
				illegalAccessError.getText(), name, annotationClass.getName(),
				field);
	}

	ReflectionError invocationTargetError(InvocationTargetException e,
			Class<? extends Annotation> annotationClass, Field field,
			String name) {
		return logException(
				new ReflectionError("Exception thrown in element", e)
						.addContextValue("name", name)
						.addContextValue("annotation", annotationClass)
						.addContextValue("field", field),
				exceptionThrownError.getText(), name,
				annotationClass.getName(), field);
	}
}
