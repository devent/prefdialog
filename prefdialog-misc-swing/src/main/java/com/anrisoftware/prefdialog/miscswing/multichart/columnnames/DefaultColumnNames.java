/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.multichart.columnnames;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ColumnNames;
import com.google.inject.assistedinject.Assisted;

public class DefaultColumnNames implements ColumnNames {

	private final String[] names;

	@Inject
	DefaultColumnNames(@Assisted String[] names) {
		this.names = names;
	}

	@Override
	public String getColumnName(int column) {
		return column < names.length ? names[column] : null;
	}

}
