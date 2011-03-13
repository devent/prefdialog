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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser;

import static java.lang.String.format;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractLabelFieldPanel;

public class FileChooserPanel extends
		AbstractLabelFieldPanel<UiFileChooserPanel> {

	public FileChooserPanel() {
		super(new UiFileChooserPanel());
		setup();
	}

	private void setup() {
		getPanelField().getFileNameText().setEditable(false);
	}

	public void setOpenFileAction(final Runnable runnable) {
		getPanelField().getOpenFileButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						runnable.run();
					}
				});
	}

	@Override
	public Object getValue() {
		return getPanelField().getFileNameText().getValue();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().getFileNameText().setValue(value);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		getPanelField().getFileNameText().setName(format("%s", name));
		getPanelField().getOpenFileButton().setName(
				format("openfilebutton-%s", name));
	}

	@Override
	public void setEnabled(boolean enabled) {
		getPanelField().getOpenFileButton().setEnabled(enabled);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	public void setFile(File file) {
		getPanelField().getFileNameText().setValue(file);
		inputChanged();
	}

}
