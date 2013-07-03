package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.miscswing.tooltip.ToolTipShower;

/**
 * Decorated the component depending whether or not the input is valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingUi extends ComponentUI {

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 0.1f);

	private final ComponentUI ui;

	private ToolTipShower toolTip;

	private Color invalidBackground;

	private boolean valid;

	private JComponent component;

	/**
	 * Sets the underlying user interface.
	 * 
	 * @param ui
	 *            the {@link ComponentUI}.
	 */
	public ValidatingUi(ComponentUI ui) {
		this.ui = ui;
		this.valid = true;
		this.invalidBackground = Color.RED;
	}

	/**
	 * Returns the installed component.
	 * 
	 * @return the {@link JComponent} or {@code null} if the user interface was
	 *         not installed or was uninstalled.
	 */
	public JComponent getComponent() {
		return component;
	}

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
	public void setInvalidBackground(Color color) {
		Color oldValue = this.invalidBackground;
		this.invalidBackground = color;
		if (oldValue != color) {
			component.repaint();
		}
	}

	/**
	 * Returns the invalid background color for the component.
	 * 
	 * @return the background {@link Color}.
	 */
	public Color getInvalidBackground() {
		return invalidBackground;
	}

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
	public void setValid(boolean valid) {
		boolean oldValue = this.valid;
		this.valid = valid;
		if (oldValue != valid) {
			component.repaint();
		}
		toolTip.setShowToolTip(!valid);
	}

	/**
	 * Returns that the input is valid.
	 * 
	 * @return {@code true} if the current input is valid.
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the invalid text that is shown in a tool-tip if the input is set as
	 * not valid.
	 * 
	 * @param text
	 *            the text {@link String}.
	 */
	public void setInvalidText(String text) {
		toolTip.setText(text);
	}

	/**
	 * Returns the invalid text that is shown in a tool-tip if the input is set
	 * as not valid.
	 * 
	 * @return the text {@link String}.
	 */
	public String getInvalidText() {
		return toolTip.getText();
	}

	@Override
	public void installUI(JComponent c) {
		ui.installUI(c);
		this.component = c;
		this.toolTip = ToolTipShower.decorate(component);
	}

	@Override
	public void uninstallUI(JComponent c) {
		ui.uninstallUI(c);
		this.component = null;
		this.toolTip = null;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		ui.paint(g, c);
		if (!valid) {
			paintInvalidOverlay(g);
		}
	}

	private void paintInvalidOverlay(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g2.setPaint(invalidBackground);
		Rectangle bounds = g.getClipBounds();
		g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	/**
	 * Call validating paint method.
	 */
	@Override
	public void update(Graphics g, JComponent c) {
		ui.update(g, c);
		paint(g, c);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return ui.getPreferredSize(c);
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return ui.getMinimumSize(c);
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return ui.getMaximumSize(c);
	}

	@Override
	public boolean contains(JComponent c, int x, int y) {
		return ui.contains(c, x, y);
	}

	@Override
	public int getBaseline(JComponent c, int width, int height) {
		return ui.getBaseline(c, width, height);
	}

	@Override
	public BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		return ui.getBaselineResizeBehavior(c);
	}

	@Override
	public int getAccessibleChildrenCount(JComponent c) {
		return ui.getAccessibleChildrenCount(c);
	}

	@Override
	public Accessible getAccessibleChild(JComponent c, int i) {
		return ui.getAccessibleChild(c, i);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(ui).toString();
	}
}
