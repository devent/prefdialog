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
package com.globalscalingsoftware.prefdialog.reflection.internal;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * A filter that only accepts predefined {@link Annotation annotations}.
 */
public class AnnotationFilter {

	private final Set<Class<? extends Annotation>> annotations;

	@Inject
	AnnotationFilter(
			@Assisted Collection<Class<? extends Annotation>> annotations) {
		this.annotations = new HashSet<Class<? extends Annotation>>(annotations);
	}

	/**
	 * Returns <code>true</code> if the given {@link Annotation} is of type of
	 * one of the predefined annotations, <code>false</code> if not.
	 */
	public boolean accept(Annotation annotation) {
		for (Class<? extends Annotation> a : annotations) {
			if (a.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

}
