package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position.Bias;
import javax.swing.text.View;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.miscswing.tooltip.ToolTipShower;

/**
 * Decorated the text component depending whether or not the input is valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ValidatingTextFieldUi extends TextUI {

	/**
	 * Sets the validating text field user interface to the specified text
	 * component.
	 * 
	 * @param field
	 *            the {@link JTextComponent}.
	 * 
	 * @return the {@link ValidatingTextFieldUi}.
	 */
	public static ValidatingTextFieldUi decorate(JTextComponent field) {
		ValidatingTextFieldUi ui = new ValidatingTextFieldUi(field.getUI(),
				ToolTipShower.decorate(field));
		field.setUI(ui);
		return ui;
	}

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 0.1f);

	private final TextUI ui;

	private final ToolTipShower toolTip;

	private Color invalidBackground;

	private boolean valid;

	private JTextComponent component;

	private String invalidText;

	/**
	 * Sets the underlying text user interface.
	 * 
	 * @param ui
	 *            the {@link TextUI}.
	 */
	public ValidatingTextFieldUi(TextUI ui, ToolTipShower toolTip) {
		this.ui = ui;
		this.toolTip = toolTip;
		this.valid = true;
		this.invalidBackground = Color.RED;
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
		this.invalidText = text;
	}

	/**
	 * Returns the invalid text that is shown in a tool-tip if the input is set
	 * as not valid.
	 * 
	 * @return the text {@link String}.
	 */
	public String getInvalidText() {
		return invalidText;
	}

	/**
	 * @see ToolTipShower#setText(String)
	 */
	public void setText(String text) {
		toolTip.setText(text);
	}

	/**
	 * @see ToolTipShower#getText()
	 */
	public String getText() {
		return toolTip.getText();
	}

	@Override
	public void installUI(JComponent c) {
		ui.installUI(c);
		component = (JTextComponent) c;
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
		paint(g, c);
	}

	@Override
	public Rectangle modelToView(JTextComponent t, int pos)
			throws BadLocationException {
		return ui.modelToView(t, pos);
	}

	@Override
	public Rectangle modelToView(JTextComponent t, int pos, Bias bias)
			throws BadLocationException {
		return ui.modelToView(t, pos, bias);
	}

	@Override
	public int viewToModel(JTextComponent t, Point pt) {
		return ui.viewToModel(t, pt);
	}

	@Override
	public int viewToModel(JTextComponent t, Point pt, Bias[] biasReturn) {
		return ui.viewToModel(t, pt, biasReturn);
	}

	@Override
	public void uninstallUI(JComponent c) {
		ui.uninstallUI(c);
	}

	@Override
	public int getNextVisualPositionFrom(JTextComponent t, int pos, Bias b,
			int direction, Bias[] biasRet) throws BadLocationException {
		return ui.getNextVisualPositionFrom(t, pos, b, direction, biasRet);
	}

	@Override
	public boolean equals(Object obj) {
		return ui.equals(obj);
	}

	@Override
	public void damageRange(JTextComponent t, int p0, int p1) {
		ui.damageRange(t, p0, p1);
	}

	@Override
	public void damageRange(JTextComponent t, int p0, int p1, Bias firstBias,
			Bias secondBias) {
		ui.damageRange(t, p0, p1, firstBias, secondBias);
	}

	@Override
	public EditorKit getEditorKit(JTextComponent t) {
		return ui.getEditorKit(t);
	}

	@Override
	public View getRootView(JTextComponent t) {
		return ui.getRootView(t);
	}

	@Override
	public String getToolTipText(JTextComponent t, Point pt) {
		return ui.getToolTipText(t, pt);
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
