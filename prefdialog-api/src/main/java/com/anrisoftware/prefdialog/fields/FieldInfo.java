/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Information about the field. The field information can be compared with
 * {@link #equals(Object)}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class FieldInfo {

	private static final String ANNOTATION = "annotation";

	private final Class<? extends Annotation> annotationType;

	/**
	 * Sets the type of annotation for which the field component can be created.
	 * 
	 * @param annotationType
	 *            the {@link Class} type.
	 */
	public FieldInfo(Class<? extends Annotation> annotationType) {
		this.annotationType = annotationType;
	}

	/**
	 * Returns the type of annotation for which the field component can be
	 * created.
	 * 
	 * @return the {@link Class} type.
	 */
	public final Class<? extends Annotation> getAnnotationType() {
		return annotationType;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(ANNOTATION, getAnnotationType()).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAnnotationType()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof FieldInfo) {
			FieldInfo rhs = (FieldInfo) obj;
			return new EqualsBuilder().append(getAnnotationType(),
					rhs.getAnnotationType()).equals(obj);
		}

		return false;
	}
}
