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
package com.anrisoftware.prefdialog.panel.api;

import java.awt.Font;

/**
 * The font of the application.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LookAndFeelFont {

	/**
	 * Returns if the user wants to change the application font.
	 */
	boolean getChangeFont();

	/**
	 * Returns the set {@link Font}.
	 */
	Font getFont();
}
