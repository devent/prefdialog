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
package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser;

import java.awt.Font;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.FontChooser;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link FontChooserPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontChooserFieldHandler extends
		AbstractLabelFieldHandler<FontChooserPanel> {

	private LoggerFactory.Logger log;
	private final JFontChooser fontChooser;

	/**
	 * Sets the parameter of the {@link FontChooserPanel}.
	 * 
	 * @param panel
	 *            the {@link FontChooserPanel}.
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
	FontChooserFieldHandler(FontChooserPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, FontChooser.class, panel);
		this.fontChooser = new JFontChooser();
	}

	/**
	 * Set the open font chooser action.
	 */
	@Override
	public FieldHandler<FontChooserPanel> setup() {
		setupOpenFontChooser();
		return super.setup();
	}

	private void setupOpenFontChooser() {
		getComponent().setOpenFileAction(new Runnable() {

			@Override
			public void run() {
				openFontChooserDialog();
			}
		});
	}

	private void openFontChooserDialog() {
		log.openFileChooserDialog(this);
		fontChooser.setSelectedFont(getValueAsFont());
		int result = fontChooser.showDialog(getAWTComponent());
		if (result == JFontChooser.OK_OPTION) {
			Font font = fontChooser.getSelectedFont();
			getComponent().setValue(font);
		}
	}

	private Font getValueAsFont() {
		return (Font) getComponent().getValue();
	}

	/**
	 * Injects the file chooser field {@link LoggerFactory}.
	 */
	@Inject
	public void setFileChooserFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(FontChooserFieldHandler.class);
	}
}
