/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
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
package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.lang.String.format;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;
import com.google.inject.Inject;

/**
 * Sets a {@link JFormattedTextField} with a {@link JButton}. In the text field
 * we can enter and show the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FileChooserPanel extends AbstractLabelFieldPanel<UiFileChooserPanel> {

	private final FileTextField fileNameText;

	/**
	 * Set the {@link UiFileChooserPanel}.
	 */
	@Inject
	FileChooserPanel(UiFileChooserPanel panel) {
		super(panel);
		this.fileNameText = getPanelField().getFileTextField();
		setup();
	}

	private void setup() {
		setupFileFormatter();
	}

	private void setupFileFormatter() {
		fileNameText.setEditable(true);
	}

	/**
	 * Set a {@link Runnable} that is called if the user clicks on the open file
	 * button.
	 */
	public void setOpenFileAction(final Runnable runnable) {
		getPanelField().getOpenFileButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						runnable.run();
					}
				});
	}

	/**
	 * Returns the {@link File} that the user have choosen.
	 */
	@Override
	public Object getValue() {
		return getPanelField().getFileTextField().getValue();
	}

	@Override
	public void setValue(Object value) {
		File file;
		if (value instanceof File) {
			file = (File) value;
		} else {
			file = new File(value.toString());
		}
		setFile(file);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		getPanelField().getFileTextField().setName(
				format("filetextfield-%s", name));
		getPanelField().getOpenFileButton().setName(
				format("openfilebutton-%s", name));
	}

	@Override
	public void setEnabled(boolean enabled) {
		getPanelField().getFileTextField().setEnabled(enabled);
		getPanelField().getOpenFileButton().setEnabled(enabled);
	}

	@Override
	public boolean isInputValid() {
		return getPanelField().getFileTextField().isEditValid();
	}

	/**
	 * Set the {@link File} that will be shown in the file name text field.
	 */
	public void setFile(File file) {
		getPanelField().getFileTextField().setValue(file);
	}

}
