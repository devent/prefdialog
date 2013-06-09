package com.anrisoftware.prefdialog.tabspanel;

import javax.swing.Icon;

/**
 * Returns the properties of a tab.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface TabsGroupRenderer {

	/**
	 * Returns the title of the tab with the specified index.
	 * 
	 * @param index
	 *            the tab index.
	 * 
	 * @return the title or {@code null}.
	 */
	String getTitle(int index);

	/**
	 * Returns the icon of the tab with the specified index.
	 * 
	 * @param index
	 *            the tab index.
	 * 
	 * @return the {@link Icon} or {@code null}.
	 */
	Icon getIcon(int index);

	/**
	 * Returns the tool-tip text of the tab with the specified index.
	 * 
	 * @param index
	 *            the tab index.
	 * 
	 * @return the tool-tip text or {@code null}.
	 */
	String getTip(int index);
}
