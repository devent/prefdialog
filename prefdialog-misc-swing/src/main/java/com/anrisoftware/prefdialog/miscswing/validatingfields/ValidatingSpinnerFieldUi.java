package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.Color;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.plaf.SpinnerUI;
import javax.swing.plaf.TextUI;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Decorated the spinner component depending whether or not the input is valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingSpinnerFieldUi extends SpinnerUI {

	/**
	 * Sets the validating spinner field user interface to the specified spinner
	 * component.
	 * 
	 * @param field
	 *            the {@link JSpinner}.
	 * 
	 * @return the {@link ValidatingSpinnerFieldUi}.
	 */
	public static ValidatingSpinnerFieldUi decorate(JSpinner field) {
		ValidatingSpinnerFieldUi ui = new ValidatingSpinnerFieldUi(
				new ValidatingComponentUI(field.getUI()), field.getUI());
		field.setUI(ui);
		return ui;
	}

	private final ValidatingComponentUI validatingUi;

	private final SpinnerUI ui;

	private ValidatingTextFieldUi editorUi;

	/**
	 * Sets the underlying text user interface.
	 * 
	 * @param ui
	 *            the {@link TextUI}.
	 */
	public ValidatingSpinnerFieldUi(ValidatingComponentUI validatingUi, SpinnerUI ui) {
		this.validatingUi = validatingUi;
		this.ui = ui;
	}

	/**
	 * @see ValidatingComponentUI#getComponent()
	 */
	public JSpinner getComponent() {
		return (JSpinner) validatingUi.getComponent();
	}

	/**
	 * @see ValidatingComponentUI#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		validatingUi.setInvalidBackground(color);
		if (editorUi != null) {
			editorUi.setInvalidBackground(color);
		}
	}

	/**
	 * @see ValidatingComponentUI#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return validatingUi.getInvalidBackground();
	}

	/**
	 * @see ValidatingComponentUI#setValid(boolean)
	 */
	public void setValid(boolean valid) {
		validatingUi.setValid(valid);
		if (editorUi != null) {
			editorUi.setValid(valid);
		}
	}

	/**
	 * @see ValidatingComponentUI#isValid()
	 */
	public boolean isValid() {
		return validatingUi.isValid();
	}

	/**
	 * @see ValidatingComponentUI#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		validatingUi.setInvalidText(text);
		if (editorUi != null) {
			editorUi.setInvalidText(text);
		}
	}

	/**
	 * @see ValidatingComponentUI#getInvalidText()
	 */
	public String getInvalidText() {
		return validatingUi.getInvalidText();
	}

	@Override
	public void installUI(JComponent c) {
		ui.installUI(c);
		validatingUi.installUI(c);
		setupEditor();
	}

	private void setupEditor() {
		JComponent editorComponent = getComponent().getEditor();
		if (editorComponent instanceof JSpinner.DefaultEditor) {
			DefaultEditor editor = (JSpinner.DefaultEditor) editorComponent;
			editorUi = ValidatingTextFieldUi.decorate(editor.getTextField());
		}
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
	public void uninstallUI(JComponent c) {
		ui.uninstallUI(c);
		validatingUi.uninstallUI(c);
		if (editorUi != null) {
			editorUi.uninstallUI(editorUi.getComponent());
		}
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
