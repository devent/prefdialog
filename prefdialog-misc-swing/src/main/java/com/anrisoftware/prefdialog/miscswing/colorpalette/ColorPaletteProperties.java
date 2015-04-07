/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.colorpalette;

import java.awt.Color;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.globalpom.format.color.ColorFormat;
import com.anrisoftware.propertiesutils.AbstractContextPropertiesProvider;

/**
 * Loads color palettes from the properties file
 * {@code color_palette.properties}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
@Singleton
class ColorPaletteProperties extends AbstractContextPropertiesProvider {

	private static final URL resource = ColorPaletteProperties.class
			.getResource("/color_palette.properties");

	@Inject
	private ColorFormat colorFormat;

	protected ColorPaletteProperties() {
		super(ColorPaletteProperties.class, resource);
	}

	public List<Color> retrieveColors(String name) throws ParseException {
		return get().getTypedListProperty(name, colorFormat);
	}
}
