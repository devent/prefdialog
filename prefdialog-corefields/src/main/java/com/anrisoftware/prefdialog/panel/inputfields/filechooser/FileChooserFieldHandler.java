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
package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.lang.String.format;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JFileChooser;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link FileChooserPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FileChooserFieldHandler extends
		AbstractLabelFieldHandler<FileChooserPanel> {

	private LoggerFactory.Logger log;

	/**
	 * Sets the parameter of the {@link FileChooserPanel}.
	 * 
	 * @param panel
	 *            the {@link FileChooserPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the {@link File} of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	@Inject
	FileChooserFieldHandler(FileChooserPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, FileChooser.class, panel);
	}

	/**
	 * Set the open file chooser action.
	 */
	@Override
	public FieldHandler<FileChooserPanel> setup() {
		setupOpenFileAction();
		return super.setup();
	}

	private void setupOpenFileAction() {
		getComponent().setOpenFileAction(new Runnable() {

			@Override
			public void run() {
				openFileChooserDialog();
			}
		});
	}

	private void openFileChooserDialog() {
		log.openFileChooserDialog(this);
		JFileChooser chooser = createFileChooserDialog();
		int option = showFileChooserDialog(chooser);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			getComponent().setFile(file);
		}
	}

	private JFileChooser createFileChooserDialog() {
		File file = (File) getComponent().getValue();
		JFileChooser chooser = new JFileChooser(file);
		chooser.setName(format("filechooser-%s", getField().getName()));
		return chooser;
	}

	private int showFileChooserDialog(JFileChooser chooser) {
		Component parent = getAWTComponent();
		int option = chooser.showOpenDialog(parent);
		return option;
	}

	/**
	 * Injects the file chooser field {@link LoggerFactory}.
	 */
	@Inject
	public void setFileChooserFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(FileChooserFieldHandler.class);
	}
}