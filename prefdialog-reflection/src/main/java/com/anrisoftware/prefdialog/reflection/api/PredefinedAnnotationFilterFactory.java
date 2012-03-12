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
package com.anrisoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;

import com.google.inject.assistedinject.Assisted;

/**
 * A factory for {@link AnnotationFilter} that accepts a collection of
 * {@link Annotation}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface PredefinedAnnotationFilterFactory {

	/**
	 * Creates a new predefined {@link AnnotationFilter}.
	 * 
	 * @param annotations
	 *            a collection of {@link Annotation annotations} that the filter
	 *            will accept.
	 * 
	 * @return the new created {@link AnnotationFilter annotation filter}.
	 */
	AnnotationFilter create(
			@Assisted Iterable<Class<? extends Annotation>> annotations);
}
