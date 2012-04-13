/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-lookfeelpanel.
 * 
 * prefdialog-lookfeelpanel is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-lookfeelpanel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-lookfeelpanel. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import java.awt.Font;

import com.anrisoftware.prefdialog.annotations.Checkbox;
import com.anrisoftware.prefdialog.annotations.FontChooser;
import com.anrisoftware.prefdialog.panel.api.LookAndFeelFont;

/**
 * Creates a {@link FontChooser} and a {@link Checkbox} field for the panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class LookAndFeelFontImpl implements LookAndFeelFont {

	@FontChooser(showTitle = false)
	private final Font font = Font.decode(null);

	@Checkbox(title = "Change the font:", text = "yes/no")
	private boolean changeFont = false;

	@Override
	public boolean getChangeFont() {
		return changeFont;
	}

	public void setChangeFont(boolean changeFont) {
		this.changeFont = changeFont;
	}

	@Override
	public Font getFont() {
		return font;
	}
}
