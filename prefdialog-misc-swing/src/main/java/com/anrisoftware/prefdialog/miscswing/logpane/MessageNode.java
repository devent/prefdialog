/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.ArrayList;
import java.util.List;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Message that should be added in a category.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class MessageNode extends AbstractMutableTreeTableNode {

	private CategoryNode category;

	private List<Object> values;

	private List<String> resourceNames;

	private Texts texts;

	/**
	 * The category must be set.
	 * 
	 * @see #setCategory(CategoryNode)
	 */
	public MessageNode() {
	}

	/**
	 * Sets the category of this message. The message have the same columns as
	 * the category.
	 * 
	 * @param category
	 *            the {@link CategoryNode}.
	 */
	public MessageNode(CategoryNode category) {
		this.category = category;
		setColumnCount(category.getColumnCount());
	}

	/**
	 * Sets the category of this message.
	 * 
	 * @param category
	 *            the {@link CategoryNode}.
	 */
	public void setCategory(CategoryNode category) {
		this.category = category;
	}

	/**
	 * Returns the category of this message.
	 * 
	 * @return the {@link CategoryNode}.
	 */
	public CategoryNode getCategory() {
		return category;
	}

	/**
	 * Sets the texts resources for the message. The column values are looked up
	 * in the resources.
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
		for (int i = 0; i < resourceNames.size(); i++) {
			try {
				String name = resourceNames.get(i);
				name = texts.getResource(name).getText();
				values.set(i, name);
			} catch (ResourcesException e) {
			}
		}
	}

	@Override
	public void setValueAt(Object value, int column) {
		if (column >= values.size()) {
			values.add(value);
			resourceNames.add(value.toString());
		} else {
			values.set(column, value);
			resourceNames.set(column, value.toString());
		}
	}

	@Override
	public Object getValueAt(int column) {
		return values.get(column);
	}

	/**
	 * Sets the columns of the message. All column values are discarded.
	 * 
	 * @param columns
	 *            the columns count.
	 */
	public void setColumnCount(int columns) {
		this.values = new ArrayList<Object>(columns);
		this.resourceNames = new ArrayList<String>(columns);
	}

	@Override
	public int getColumnCount() {
		return category.getColumnCount();
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
}
