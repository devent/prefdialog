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
