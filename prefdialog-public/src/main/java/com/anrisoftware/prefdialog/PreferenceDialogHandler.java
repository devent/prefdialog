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
 * <p>
 * Sets the children model and panels, reacts to selection and sets the current
 * selected child panel.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface PreferenceDialogHandler {

	/**
	 * Returns the used preference dialog.
	 * 
	 * @return the {@link PreferenceDialog}.
	 * 
	 * @since 2.2
	 */
	PreferenceDialog getDialog();

	/**
	 * Returns the used children panel.
	 * 
	 * @return the {@link ChildrenPanel}.
	 * 
	 * @since 2.2
	 */
	ChildrenPanel getPanel();

	/**
	 * Returns the used children model.
	 * 
	 * @return the used {@link ListModel} that contains the children objects.
	 * 
	 * @since 2.2
	 */
	ListModel getChildrenModel();

	/**
	 * Returns the used panels that will return a panel for the selected child.
	 * 
	 * @return the used {@link ChildrenPanel}, that returns the {@link JPanel}
	 *         for the currently selected child panel.
	 * 
	 * @since 2.2
	 */
	ChildrenPanels getChildrenPanels();
}
