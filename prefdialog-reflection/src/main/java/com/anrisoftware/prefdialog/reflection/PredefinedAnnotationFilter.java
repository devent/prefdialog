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
package com.anrisoftware.prefdialog.reflection;

import static com.google.common.collect.Sets.newHashSet;

import java.lang.annotation.Annotation;
import java.util.Set;

import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * A filter that only accepts predefined {@link Annotation}s from a given
 * collection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class PredefinedAnnotationFilter implements AnnotationFilter {

	private final Set<Class<? extends Annotation>> annotations;

	/**
	 * Set the {@link Annotation} collection that will be accepted.
	 */
	@Inject
	PredefinedAnnotationFilter(
			@Assisted Iterable<Class<? extends Annotation>> annotations) {
		this.annotations = newHashSet(annotations);
	}

	@Override
	public boolean accept(Annotation annotation) {
		for (Class<? extends Annotation> a : annotations) {
			if (a.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

}
