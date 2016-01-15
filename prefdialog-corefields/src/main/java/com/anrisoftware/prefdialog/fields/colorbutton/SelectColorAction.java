/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.colorbutton;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

/**
 * Opens a color chooser dialog upon action.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class SelectColorAction extends AbstractAction {

	public static final String COLOR_PROPERTY = "color_property";

	private Color color;

	private String title;

	/**
	 * Sets the title of the color chooser dialog.
	 * 
	 * @param newTile
	 *            the title.
	 */
	public void setTitle(String newTile) {
		title = newTile;
	}

	/**
	 * Sets the initial color for the color chooser dialog.
	 * 
	 * @param newColor
	 *            the {@link Color}.
	 */
	public void setColor(Color newColor) {
		Color oldValue = color;
		color = newColor;
		firePropertyChange(COLOR_PROPERTY, oldValue, newColor);
	}

	/**
	 * Returns the selected color from the color chooser dialog.
	 * 
	 * @return the {@link Color}.
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component parent = (Component) e.getSource();
		setColor(openColorDialog(parent));
	}

	private Color openColorDialog(Component parent) {
		Color selectedColor = JColorChooser.showDialog(parent, title, color);
		return selectedColor == null ? color : selectedColor;
	}

}
