/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.tabspanel;

import javax.swing.Icon;

/**
 * Returns the properties of a tab.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface TabsRenderer {

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
