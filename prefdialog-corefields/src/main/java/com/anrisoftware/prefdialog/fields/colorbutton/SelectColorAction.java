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
