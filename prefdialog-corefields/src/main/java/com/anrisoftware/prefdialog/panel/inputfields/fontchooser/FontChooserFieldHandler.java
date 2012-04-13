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
package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.FontChooser;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link FieldPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class FontChooserFieldHandler extends
		AbstractLabelFieldHandler<FieldPanel> {

	/**
	 * Name prefix for the open font button.
	 */
	public static final String OPEN_FONT_BUTTON = "openfontbutton";
	/**
	 * Name prefix for the font combo box.
	 */
	public static final String FONTBOX = "fontbox";

	/**
	 * Sets the parameter of the {@link FieldPanel}.
	 * 
	 * @param panel
	 *            the {@link FieldPanel}.
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
	FontChooserFieldHandler(FieldPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, FontChooser.class, panel);
	}

	/**
	 * Sets the minimum font chooser panel height.
	 */
	@Override
	public FieldHandler<FieldPanel> setup() {
		setupMinimumHeight();
		return super.setup();
	}

	private void setupMinimumHeight() {
		int height = valueFromA("minimumHeight", Integer.class);
		getComponent().setMinimumFontChooserHeight(height);
	}

}
