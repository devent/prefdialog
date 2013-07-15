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
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.accessibility.Accessible;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Decorated the combo-box component depending whether or not the input is
 * valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingComboBoxUi extends BasicComboBoxUI {

	/**
	 * Sets the validating combo-box field user interface to the specified
	 * combo-box component.
	 * 
	 * @param field
	 *            the {@link JComboBox}.
	 * 
	 * @return the {@link ValidatingComboBoxUi}.
	 */
	public static ValidatingComboBoxUi decorate(JComboBox<?> field) {
		ValidatingComboBoxUi ui = new ValidatingComboBoxUi(
				new ValidatingComponent(field.getUI()),
				(BasicComboBoxUI) field.getUI());
		field.setUI(ui);
		return ui;
	}

	private final ValidatingComponent validatingUi;

	private final BasicComboBoxUI ui;

	private ValidatingTextFieldUi editorUi;

	/**
	 * Sets the underlying combo-box user interface.
	 * 
	 * @param ui
	 *            the {@link BasicComboBoxUI}.
	 */
	public ValidatingComboBoxUi(ValidatingComponent validatingUi,
			BasicComboBoxUI ui) {
		this.validatingUi = validatingUi;
		this.ui = ui;
	}

	/**
	 * @see ValidatingComponent#getComponent()
	 */
	public JComboBox<?> getComponent() {
		return (JComboBox<?>) validatingUi.getComponent();
	}

	/**
	 * @see ValidatingComponent#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		validatingUi.setInvalidBackground(color);
		if (editorUi != null) {
			editorUi.setInvalidBackground(color);
		}
	}

	/**
	 * @see ValidatingComponent#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return validatingUi.getInvalidBackground();
	}

	/**
	 * @see ValidatingComponent#setValid(boolean)
	 */
	public void setValid(boolean valid) {
		validatingUi.setValid(valid);
		if (editorUi != null) {
			editorUi.setValid(valid);
		}
	}

	/**
	 * @see ValidatingComponent#isValid()
	 */
	public boolean isValid() {
		return validatingUi.isValid();
	}

	/**
	 * @see ValidatingComponent#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		validatingUi.setInvalidText(text);
		if (editorUi != null) {
			editorUi.setInvalidText(text);
		}
	}

	/**
	 * @see ValidatingComponent#getInvalidText()
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
		if (!getComponent().isEditable()) {
			return;
		}
		Component editorComponent = getComponent().getEditor()
				.getEditorComponent();
		if (editorComponent instanceof JTextComponent) {
			JTextComponent text = (JTextComponent) editorComponent;
			editorUi = ValidatingTextFieldUi.decorate(text);
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
	public int hashCode() {
		return ui.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ui.equals(obj);
	}

	@Override
	public void addEditor() {
		ui.addEditor();
	}

	@Override
	public void removeEditor() {
		ui.removeEditor();
	}

	@Override
	public void configureArrowButton() {
		ui.configureArrowButton();
	}

	@Override
	public void unconfigureArrowButton() {
		ui.unconfigureArrowButton();
	}

	@Override
	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
		ui.paintCurrentValue(g, bounds, hasFocus);
	}

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds,
			boolean hasFocus) {
		ui.paintCurrentValueBackground(g, bounds, hasFocus);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(ui).toString();
	}
}
