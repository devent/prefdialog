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

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Category of messages.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CategoryNode extends AbstractMutableTreeTableNode {

	private String name;

	private Texts texts;

	private String nameResource;

	private int columnCount;

	/**
	 * Sets the name of the category.
	 * 
	 * @param name
	 *            the {@link String} name or the resource name for the category.
	 */
	public void setName(String name) {
		this.nameResource = name;
		this.name = name;
	}

	/**
	 * Sets the texts resources for the category. The name is looked up in the
	 * resources.
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
		updateTextsResource();
	}

	private void updateTextsResource() {
		if (texts == null) {
			return;
		}
		try {
			name = texts.getResource(nameResource).getText();
		} catch (ResourcesException e) {
		}
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
