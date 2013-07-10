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
package com.anrisoftware.prefdialog.miscswing.tooltip;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.text.JTextComponent;

/**
 * Shows an artificial tool-tip for a component. The text of the tool-tip can be
 * set independently of the original tool-tip.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ToolTipShower {

	/**
	 * Sets the component to show the artificial tool-tip.
	 * 
	 * @param component
	 *            the {@link JTextComponent}.
	 * 
	 * @return the {@link ToolTipShower}.
	 */
	public static ToolTipShower decorate(JComponent component) {
		ToolTipShower shower = new ToolTipShower(component);
		return shower;
	}

	private final JComponent component;

	private boolean oldToolTipTextSet;

	private String oldToolTipText;

	private String text;

	/**
	 * Sets the underlying component for which to show the tool-tip.
	 * 
	 * @param component
	 *            the {@link JComponent}.
	 */
	public ToolTipShower(JComponent component) {
		this.component = component;
		this.oldToolTipTextSet = false;
		this.oldToolTipText = null;
	}

	/**
	 * Sets the tool-tip text.
	 * 
	 * @param text
	 *            the tool-tip text {@link String}.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the tool-tip text.
	 * 
	 * @return the tool-tip text {@link String}.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Shows or hides the tool-tip.
	 * 
	 * @param show
	 *            set to {@code true} to show the tool-tip.
	 */
	public void setShowToolTip(boolean show) {
		if (show) {
			showToolTip();
		} else {
			hideToolTip();
		}
	}

	private void showToolTip() {
		if (!oldToolTipTextSet) {
			oldToolTipText = component.getToolTipText();
			oldToolTipTextSet = true;
		}
		component.setToolTipText(text);
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

	private void hideToolTip() {
		component.setToolTipText(oldToolTipText);
		oldToolTipText = null;
		oldToolTipTextSet = false;
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

}
