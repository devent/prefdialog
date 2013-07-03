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
public class ValidatingComponentUI extends ComponentUI implements ValidatingUI {

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
	public ValidatingComponentUI(ComponentUI ui) {
		this.ui = ui;
		this.valid = true;
		this.invalidBackground = Color.RED;
	}

	@Override
	public JComponent getComponent() {
		return component;
	}

	@Override
	public void setInvalidBackground(Color color) {
		Color oldValue = this.invalidBackground;
		this.invalidBackground = color;
		if (oldValue != color) {
			component.repaint();
		}
	}

	@Override
	public Color getInvalidBackground() {
		return invalidBackground;
	}

	@Override
	public void setValid(boolean valid) {
		boolean oldValue = this.valid;
		this.valid = valid;
		if (oldValue != valid) {
			component.repaint();
		}
		toolTip.setShowToolTip(!valid);
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public void setInvalidText(String text) {
		toolTip.setText(text);
	}

	@Override
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
