/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.colorbutton;

import static com.anrisoftware.prefdialog.fields.colorbutton.SelectColorAction.COLOR_PROPERTY;
import static java.lang.String.format;

import java.awt.Color;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import com.anrisoftware.prefdialog.annotations.ColorButton;
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.buttongroup.ButtonsGroupPanel;
import com.anrisoftware.prefdialog.fields.buttongroup.ButtonsRowPanel;
import com.google.inject.assistedinject.Assisted;

/**
 * Color button field. Button to select a color value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class ColorButtonField extends
		AbstractTitleField<ButtonsGroupPanel, Container> {

	private static final String HORIZONTAL_ALIGNMENT_ELEMENT = "horizontalAlignment";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ColorButton.class;

	private final ColorButtonFieldLogger log;

	private final ButtonsRowPanel buttonsRowPanel;

	@SuppressWarnings("rawtypes")
	private final DefaultListModel rowModel;

	private final SelectColorAction selectColorAction;

	private JButton colorButton;

	@Inject
	ColorButtonField(ColorButtonFieldLogger logger,
			ButtonsGroupPanel buttonsPanel, ButtonsRowPanel buttonsRowPanel,
			SelectColorAction selectColorAction, @Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(buttonsPanel, container, parentObject, field);
		this.log = logger;
		this.buttonsRowPanel = buttonsRowPanel;
		this.rowModel = new DefaultListModel();
		this.selectColorAction = selectColorAction;
		setup();
	}

	private void setup() {
		selectColorAction
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (evt.getPropertyName() == COLOR_PROPERTY) {
							setupColorValue((Color) evt.getNewValue());
						}
					}
				});
		getComponent().setButtonsRowPanel(buttonsRowPanel);
		rowModel.addElement(selectColorAction);
		buttonsRowPanel.setModel(rowModel);
		colorButton = buttonsRowPanel.getButton(0);
	}

	@Override
	public ColorButtonField createField() {
		super.createField();
		setupHorizontalAlignment();
		return this;
	}

	private void setupHorizontalAlignment() {
		HorizontalAlignment alignment = getAnnotationAccess().getValue(
				ANNOTATION_CLASS, getField(), HORIZONTAL_ALIGNMENT_ELEMENT);
		setHorizontalAlignment(alignment);
	}

	@Override
	public void applyInput() throws PropertyVetoException {
		super.applyInput();
		setValue(selectColorAction.getColor());
	}

	@Override
	public void restoreInput() {
		super.restoreInput();
		setValue(getValue());
	}

	/**
	 * Sets the color value of this color button.
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not of type {@link Color}.
	 */
	@Override
	public void setValue(Object newValue) {
		super.setValue(newValue);
		log.checkValueIsColor(this, newValue);
		setupColorValue((Color) newValue);
	}

	/**
	 * Sets the color value of this color button.
	 * 
	 * @param newColor
	 *            the {@link Color} value.
	 */
	public void setValue(Color newColor) {
		super.setValue(newColor);
		setupColorValue(newColor);
	}

	private void setupColorValue(Color newColor) {
		selectColorAction.setColor(newColor);
		colorButton.setBackground(newColor);
		colorButton.setForeground(textColorContrastYIQ(newColor));
		colorButton.setText(colorToString(newColor));
	}

	private String colorToString(Color color) {
		return format("#%s", colorToHexString(color));
	}

	private String colorToHexString(Color color) {
		int value = color.getRGB() & 0x00ffffff;
		return Integer.toHexString(0x1000000 | value).substring(1);
	}

	private Color textColorContrastYIQ(Color color) {
		int r = color.getRed();
		int b = color.getBlue();
		int g = color.getGreen();
		int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
		return (yiq >= 128) ? Color.BLACK : Color.WHITE;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		getComponent().setName(name);
		buttonsRowPanel.setName(name);
	}

	@Override
	public void setTitle(String newTitle) {
		super.setTitle(newTitle);
		selectColorAction.setTitle(getTitle());
	}

	/**
	 * Sets the horizontal alignment of the color button.
	 * 
	 * @param alignment
	 *            the {@link HorizontalAlignment}.
	 */
	public void setHorizontalAlignment(HorizontalAlignment alignment) {
		getComponent().setHorizontalAlignment(alignment);
		log.horizontalAlignmentSet(this, alignment);
	}

	/**
	 * Returns the horizontal alignment of the color button.
	 * 
	 * @return the {@link HorizontalAlignment}.
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return getComponent().getHorizontalAlignment();
	}
}
