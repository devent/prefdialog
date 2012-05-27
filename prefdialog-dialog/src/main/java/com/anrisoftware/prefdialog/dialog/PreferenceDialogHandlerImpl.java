/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog;

import javax.swing.ListModel;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;
import com.anrisoftware.prefdialog.PreferenceDialog;
import com.anrisoftware.prefdialog.PreferenceDialogHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class PreferenceDialogHandlerImpl implements PreferenceDialogHandler {

	private final PreferenceDialog dialog;

	private final ChildrenPanel panel;

	private final ListModel childrenModel;

	private final ChildrenPanels childrenPanels;

	@Inject
	PreferenceDialogHandlerImpl(@Assisted PreferenceDialog dialog,
			@Assisted ChildrenPanel panel, @Assisted ListModel childrenModel,
			@Assisted ChildrenPanels childrenPanels) {
		this.dialog = dialog;
		this.panel = panel;
		this.childrenModel = childrenModel;
		this.childrenPanels = childrenPanels;
		setupDialog();
	}

	private void setupDialog() {
	}

	@Override
	public PreferenceDialog getDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildrenPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListModel getChildrenModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildrenPanels getChildrenPanels() {
		// TODO Auto-generated method stub
		return null;
	}

}
