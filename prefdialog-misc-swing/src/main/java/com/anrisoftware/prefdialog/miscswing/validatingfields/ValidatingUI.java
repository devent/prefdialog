/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.Color;

import javax.swing.JComponent;

public interface ValidatingUI {

	/**
	 * Returns the installed component.
	 * 
	 * @return the {@link JComponent} or {@code null} if the user interface was
	 *         not installed or was uninstalled.
	 */
	JComponent getComponent();

	/**
	 * Sets the invalid background color for the component.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called on the AWT thread.
	 * 
	 * @param color
	 *            the background {@link Color}.
	 */
	void setInvalidBackground(Color color);

	/**
	 * Returns the invalid background color for the component.
	 * 
	 * @return the background {@link Color}.
	 */
	Color getInvalidBackground();

	/**
	 * Sets that the input is valid. The component is repaint when the state
	 * changed.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called on the AWT thread.
	 * 
	 * @param valid
	 *            set to {@code true} if the current input is valid.
	 */
	void setValid(boolean valid);

	/**
	 * Returns that the input is valid.
	 * 
	 * @return {@code true} if the current input is valid.
	 */
	boolean isValid();

	/**
	 * Sets the invalid text that is shown in a tool-tip if the input is set as
	 * not valid.
	 * 
	 * @param text
	 *            the text {@link String}.
	 */
	void setInvalidText(String text);

	/**
	 * Returns the invalid text that is shown in a tool-tip if the input is set
	 * as not valid.
	 * 
	 * @return the text {@link String}.
	 */
	String getInvalidText();

}