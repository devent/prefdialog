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
package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import javax.swing.ListCellRenderer;

/**
 * Factory to create a render for default items of the combo box.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface ItemsDefaultComboBoxRendererFactory {

	/**
	 * Create the default items renderer from the parent renderer.
	 * 
	 * @param renderer
	 *            the parent {@link ListCellRenderer}.
	 * 
	 * @return the default items {@link ListCellRenderer}.
	 */
	@SuppressWarnings("rawtypes")
	ListCellRenderer create(ListCellRenderer renderer);
}
