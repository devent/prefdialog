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
package com.globalscalingsoftware.prefdialog.panel.inputfields.color;

import java.awt.Color;
import java.lang.reflect.Field;

import javax.swing.JColorChooser;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link ColorPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorFieldHandler extends AbstractLabelFieldHandler<ColorPanel> {

	private LoggerFactory.Logger log;

	/**
	 * Sets the parameter of the {@link ColorPanel}.
	 * 
	 * @param panel
	 *            the {@link ColorPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	@Inject
	ColorFieldHandler(ColorPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field,
				com.globalscalingsoftware.prefdialog.annotations.Color.class,
				panel);
	}

	/**
	 * Set the open color panel action.
	 */
	@Override
	public FieldHandler<ColorPanel> setup() {
		getComponent().setOpenColorChoserAction(new Runnable() {

			@Override
			public void run() {
				openFileChooserDialog();
			}
		});
		return super.setup();
	}

	private void openFileChooserDialog() {
		log.openColorChooserDialog(this);
		Color color = openColorDialog();
		getComponent().setValue(color);
	}

	private Color openColorDialog() {
		String title = getComponent().getTitle();
		Color color = getValueAsColor();
		return JColorChooser.showDialog(getAWTComponent(), title, color);
	}

	private Color getValueAsColor() {
		return (Color) getComponent().getValue();
	}

	/**
	 * Injects the file chooser field {@link LoggerFactory}.
	 */
	@Inject
	public void setFileChooserFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(ColorFieldHandler.class);
	}
}
