/*
 * Copyright 2010-2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog;

import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 * Factory to create a new {@link PreferenceDialogHandler}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface PreferenceDialogHandlerFactory {

	/**
	 * Creates a new {@link PreferenceDialogHandler}.
	 * 
	 * @param dialog
	 *            the used {@link PreferenceDialog}, that will contain the panel
	 *            and the dialog buttons.
	 * 
	 * @param panel
	 *            the user {@link ChildrenPanel}, that displays a list of the
	 *            children and the current selected child panel.
	 * 
	 * @param childrenModel
	 *            the used {@link ListModel} that contains the children objects.
	 * 
	 * @param childrenPanels
	 *            the used {@link ChildrenPanel}, that returns the
	 *            {@link JPanel} for the currently selected child panel.
	 * 
	 * @since 2.2
	 */
	PreferenceDialogHandler create(PreferenceDialog dialog,
			ChildrenPanel panel, ListModel childrenModel,
			ChildrenPanels childrenPanels);
}
