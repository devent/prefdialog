/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.core;

import java.util.Comparator;

import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Compares two fields based on their order number.
 * 
 * @see FieldComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class FieldComponentComparator implements Comparator<FieldComponent<?>> {

	@Override
	public int compare(FieldComponent<?> o1, FieldComponent<?> o2) {
		return o1.getOrder() < o2.getOrder() ? -1 : o1.getOrder() > o2
				.getOrder() ? 1 : 0;
	}

}
