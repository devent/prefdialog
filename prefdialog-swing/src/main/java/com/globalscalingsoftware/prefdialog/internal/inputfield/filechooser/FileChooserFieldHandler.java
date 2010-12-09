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
package com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser;

import static java.lang.String.format;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JFileChooser;

import com.globalscalingsoftware.prefdialog.annotations.FileChooser;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class FileChooserFieldHandler extends
		AbstractDefaultFieldHandler<FileChooserPanel> {

	@Inject
	FileChooserFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, FileChooser.class,
				new FileChooserPanel());
	}

	@Override
	public void setup() {
		super.setup();
		getComponent().setOpenFileAction(new Runnable() {

			@Override
			public void run() {
				openFileChooserDialog();
			}
		});
	}

	private void openFileChooserDialog() {
		JFileChooser chooser = createFileChooserDialog();
		int option = showFileChooserDialog(chooser);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			getComponent().setValue(file);
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

}
