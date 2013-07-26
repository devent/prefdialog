package com.anrisoftware.prefdialog.miscswing.problemspane;

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
