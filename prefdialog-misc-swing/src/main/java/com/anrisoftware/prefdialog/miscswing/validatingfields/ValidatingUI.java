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