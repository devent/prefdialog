package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.accessibility.Accessible;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Decorated the combo-box component depending whether or not the input is
 * valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingComboBoxFieldUi extends ComboBoxUI {

	/**
	 * Sets the validating combo-box field user interface to the specified
	 * combo-box component.
	 * 
	 * @param field
	 *            the {@link JComboBox}.
	 * 
	 * @return the {@link ValidatingComboBoxFieldUi}.
	 */
	public static ValidatingComboBoxFieldUi decorate(JComboBox<?> field) {
		ValidatingComboBoxFieldUi ui = new ValidatingComboBoxFieldUi(
				new ValidatingUi(field.getUI()), field.getUI());
		field.setUI(ui);
		return ui;
	}

	private final ValidatingUi validatingUi;

	private final ComboBoxUI ui;

	/**
	 * Sets the underlying combo-box user interface.
	 * 
	 * @param ui
	 *            the {@link ComboBoxUI}.
	 */
	public ValidatingComboBoxFieldUi(ValidatingUi validatingUi, ComboBoxUI ui) {
		this.validatingUi = validatingUi;
		this.ui = ui;
	}

	/**
	 * @see ValidatingUi#getComponent()
	 */
	public JComboBox<?> getComponent() {
		return (JComboBox<?>) validatingUi.getComponent();
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

	/**
	 * @see ValidatingUi#setText(String)
	 */
	public void setText(String text) {
		validatingUi.setText(text);
	}

	/**
	 * @see ValidatingUi#getText()
	 */
	public String getText() {
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
	@SuppressWarnings("rawtypes")
	public void setPopupVisible(JComboBox c, boolean v) {
		ui.setPopupVisible(c, v);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean isPopupVisible(JComboBox c) {
		return ui.isPopupVisible(c);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean isFocusTraversable(JComboBox c) {
		return ui.isFocusTraversable(c);
	}

	@Override
	public void uninstallUI(JComponent c) {
		ui.uninstallUI(c);
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
