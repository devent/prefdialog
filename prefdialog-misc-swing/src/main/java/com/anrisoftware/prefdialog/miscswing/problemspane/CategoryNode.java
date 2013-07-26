package com.anrisoftware.prefdialog.miscswing.problemspane;

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
