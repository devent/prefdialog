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

/**
 * Search an object's fields for {@link Annotation}s and if an annotation is
 * found it will call the {@link AnnotationDiscoveryCallback} callback. The
 * annotations are defined by a {@link AnnotationFilter}.
 * 
 * @see AnnotationFilter
 * @see AnnotationDiscoveryCallback
 * @see Annotation
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface AnnotationDiscoveryWorker {

	/**
	 * Discovers the {@link Annotation}s in the given object's fields.
	 * 
	 * @param object
	 *            the object.
	 */
	void discoverAnnotations(Object object);
}
