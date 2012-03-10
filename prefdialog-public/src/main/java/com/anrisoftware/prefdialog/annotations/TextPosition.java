/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

/**
 * The text and icon positions for the {@link FileChooser} button.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public enum TextPosition {

	/**
	 * Show only the icon.
	 */
	ICON_ONLY,

	/**
	 * Show only the text.
	 */
	TEXT_ONLY,

	/**
	 * Text alongside the icon.
	 */
	TEXT_ALONGSIDE_ICON,

	/**
	 * Text is under the icon.
	 */
	TEXT_UNDER_ICON
}
