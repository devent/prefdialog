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
import java.beans.VetoableChangeListener;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.annotations.ColorButton;
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.buttongroup.ButtonsGroupPanel;
import com.anrisoftware.prefdialog.fields.buttongroup.ButtonsRowPanel;
import com.anrisoftware.prefdialog.miscswing.components.validating.AbstractValidatingComponent;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingTextComponent;
import com.google.inject.assistedinject.Assisted;

/**
 * Color button field. Button to select a color value.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ColorButtonField extends
		AbstractTitleField<ButtonsGroupPanel, Container> {

	private static final String HORIZONTAL_ALIGNMENT_ELEMENT = "horizontalAlignment";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ColorButton.class;

	private final ColorButtonFieldLogger log;

	private final ButtonsRowPanel buttonsRowPanel;

	private final DefaultListModel<Action> rowModel;

	private final SelectColorAction selectColorAction;

	private JButton colorButton;

	private final ButtonsGroupPanel buttonsGroupPanel;

	private AnnotationAccess fieldAnnotation;

	private final PropertyChangeListener colorListener;

	private final VetoableChangeListener valueVetoListener;

	private AbstractValidatingComponent<Color, JComponent> validating;

	/**
	 * @see ColorButtonFieldFactory#create(Container, Object, String)
	 */
	@Inject
	ColorButtonField(ColorButtonFieldLogger logger,
			ButtonsGroupPanel buttonsGroupPanel,
			ButtonsRowPanel buttonsRowPanel,
			SelectColorAction selectColorAction, @Assisted Container container,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(buttonsGroupPanel, container, parentObject, fieldName);
		this.log = logger;
		this.buttonsGroupPanel = buttonsGroupPanel;
		this.buttonsRowPanel = buttonsRowPanel;
		this.rowModel = new DefaultListModel<Action>();
		this.selectColorAction = selectColorAction;
		this.validating = new AbstractValidatingComponent<Color, JComponent>(
				buttonsGroupPanel) {

			@Override
			protected void setComponentValue(Color value) {
				setupColorValue(value);
			}

			@Override
			protected Color getComponentValue() {
				return ColorButtonField.this.getColor();
			}
		};
		this.colorListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == COLOR_PROPERTY) {
					validating.setValue((Color) evt.getNewValue());
				}
			}
		};
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				ColorButtonField.super.trySetValue(evt.getNewValue());
				changeValue(evt.getNewValue());
			}
		};
		setup();
	}

	private void setup() {
		selectColorAction.addPropertyChangeListener(colorListener);
		buttonsGroupPanel.setButtonsRowPanel(buttonsRowPanel);
		rowModel.addElement(selectColorAction);
		buttonsRowPanel.setModel(rowModel);
		colorButton = buttonsRowPanel.getButton(0);
		validating.addVetoableChangeListener(
				ValidatingTextComponent.VALUE_PROPERTY, valueVetoListener);
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory) {
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_CLASS,
				getAccessibleObject());
		setupHorizontalAlignment();
	}

	private void setupHorizontalAlignment() {
		HorizontalAlignment alignment = fieldAnnotation
				.getValue(HORIZONTAL_ALIGNMENT_ELEMENT);
		setHorizontalAlignment(alignment);
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
		buttonsGroupPanel.setHorizontalAlignment(alignment);
		log.horizontalAlignmentSet(this, alignment);
	}

	/**
	 * Returns the horizontal alignment of the color button.
	 * 
	 * @return the {@link HorizontalAlignment}.
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return buttonsGroupPanel.getHorizontalAlignment();
	}

	/**
	 * Sets the color value of this color button.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not of type {@link Color}.
	 */
	@Override
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		log.checkValueIsColor(this, value);
		setupColorValue((Color) value);
	}

	/**
	 * Sets the color value of this color button.
	 * 
	 * @param color
	 *            the {@link Color} value.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 */
	public void setValue(Color color) throws PropertyVetoException {
		super.setValue(color);
		setupColorValue(color);
	}

	/**
	 * Returns the color value.
	 * 
	 * @return the {@link Color}.
	 */
	public Color getColor() {
		return (Color) getValue();
	}

	private void setupColorValue(Color color) {
		selectColorAction.setColor(color);
		colorButton.setBackground(color);
		colorButton.setForeground(textColorContrastYIQ(color));
		colorButton.setText(colorToString(color));
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

}
