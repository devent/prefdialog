/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.logpane;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

/**
 * Root node.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class RootNode extends AbstractMutableTreeTableNode {

	private String name;

	private int columnCount;

	/**
	 * Sets the name of the category.
	 * 
	 * @param name
	 *            the {@link String} name or the resource name for the category.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getValueAt(int column) {
		if (column == 0) {
			return name;
		}
		return null;
	}

	/**
	 * Sets the columns of the category.
	 * 
	 * @param columns
	 *            the columns count.
	 */
	public void setColumnCount(int columns) {
		this.columnCount = columns;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
