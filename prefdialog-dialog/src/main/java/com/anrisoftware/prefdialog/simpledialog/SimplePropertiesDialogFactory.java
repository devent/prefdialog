/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a simple dialog.
 * 
 * @see SimplePropertiesDialog
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface SimplePropertiesDialogFactory {

	/**
	 * Creates the simple dialog from the specified properties.
	 * 
	 * @param properties
	 *            the {@link Object} properties of the dialog.
	 * 
	 * @param panelFieldName
	 *            the name of the field that is annotated as
	 *            {@link VerticalPreferencesPanel}.
	 * 
	 * @return the {@link SimplePropertiesDialog}.
	 */
	SimplePropertiesDialog create(@Assisted("properties") Object properties,
			@Assisted("panelFieldName") String panelFieldName);
}
