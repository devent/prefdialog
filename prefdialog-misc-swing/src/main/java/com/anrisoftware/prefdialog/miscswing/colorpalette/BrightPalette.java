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
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * Bright colors palette.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class BrightPalette implements ColorPalette {

	private static final String PALETTE_NAME = "bright_palette";

	@Inject
	private ColorPaletteProperties palette;

	@Override
	public Iterator<Color> iterator() {
		return new Iterator<Color>() {

			private final List<Color> colors = getPalette();

			private final int size = colors.size();

			private int index = 0;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Color next() {
				int s = size;
				int i = index;
				if (i >= s) {
					i = 0;
				}
				Color color = colors.get(i++);
				index = i;
				return color;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	private List<Color> getPalette() {
		try {
			return palette.retrieveColors(PALETTE_NAME);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
