package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
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

/**
 * Decorated the text component depending whether or not the input is valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
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
		ValidatingTextFieldUi ui = new ValidatingTextFieldUi(new ValidatingUi(
				field.getUI()), field.getUI());
		field.setUI(ui);
		return ui;
	}

	private final ValidatingUi validatingUi;

	private final TextUI ui;

	/**
	 * Sets the underlying text user interface.
	 * 
	 * @param ui
	 *            the {@link TextUI}.
	 */
	public ValidatingTextFieldUi(ValidatingUi validatingUi, TextUI ui) {
		this.validatingUi = validatingUi;
		this.ui = ui;
	}

	/**
	 * @see ValidatingUi#getComponent()
	 */
	public JTextComponent getComponent() {
		return (JTextComponent) validatingUi.getComponent();
	}

	/**
	 * @see ValidatingUi#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		validatingUi.setInvalidBackground(color);
	}

	/**
	 * @see ValidatingUi#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return validatingUi.getInvalidBackground();
	}

	/**
	 * @see ValidatingUi#setValid(boolean)
	 */
	public void setValid(boolean valid) {
		validatingUi.setValid(valid);
	}

	/**
	 * @see ValidatingUi#isValid()
	 */
	public boolean isValid() {
		return validatingUi.isValid();
	}

	/**
	 * @see ValidatingUi#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		validatingUi.setInvalidText(text);
	}

	/**
	 * @see ValidatingUi#getInvalidText()
	 */
	public String getInvalidText() {
		return validatingUi.getInvalidText();
	}

	@Override
	public void installUI(JComponent c) {
		ui.installUI(c);
		validatingUi.installUI(c);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		ui.paint(g, c);
		validatingUi.paint(g, c);
	}

	@Override
	public void update(Graphics g, JComponent c) {
		validatingUi.update(g, c);
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
