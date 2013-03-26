/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser;

import java.awt.Container;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

/**
 * Field to show and select a file. Opens a dialog to select the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@SuppressWarnings("rawtypes")
public class FileChooserField extends AbstractTitleField<JPanel, Container> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = FileChooser.class;

	private final FileChooserFieldLogger log;

	@Inject
	FileChooserField(FileChooserFieldLogger logger, UiPanel panel,
			@Assisted Container container, @Assisted Object parentObject,
			@Assisted Field field) {
		super(((UiPanel) panel.run()).getPanel(), container, parentObject,
				field);
		this.log = logger;
	}

	@Override
	protected void afterName() {
		super.afterName();
	}

	@Override
	public void applyInput() throws PropertyVetoException {
		super.applyInput();
	}

	@Override
	public void restoreInput() {
		super.restoreInput();
	}

	@Override
	public void setValue(Object newValue) {
		super.setValue(newValue);
	}

}
