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
package com.anrisoftware.prefdialog.fields.fieldbutton;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClass;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.annotations.FieldButton;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Abstract button field.
 * 
 * @see FieldButton
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class AbstractFieldButtonField<ComponentType extends AbstractButton>
		extends AbstractTitleField<ComponentType> {

	private static final Class<? extends Annotation> ANNOTATION_TYPE = FieldButton.class;

	private static final String TEXT_ELEMENT = "text";

	private static final String SHOW_TEXT_ELEMENT = "showText";

	private static final String ACTION_ELEMENT = "action";

	private static final String GROUP_ELEMENT = "group";

	private transient AnnotationAccess fieldAnnotation;

	private transient AnnotationClass<?> annotationClass;

	private AbstractFieldButtonFieldLogger log;

	private String textResource;

	private String text;

	private boolean showText;

	private ButtonGroup buttonGroup;

	/**
	 * Sets the dependencies of the field button.
	 * 
	 * @see AbstractTitleField#AbstractTitleField(java.awt.Component, Object,
	 *      String)
	 */
	protected AbstractFieldButtonField(ComponentType component,
			Object parentObject, String fieldName) {
		super(component, parentObject, fieldName);
	}

	@Inject
	void setupAbstractFieldButton(AbstractFieldButtonFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			AnnotationClassFactory annotationClassFactory) {
		this.log = logger;
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_TYPE,
				getAccessibleObject());
		this.annotationClass = annotationClassFactory.create(getParentObject(),
				ANNOTATION_TYPE, getAccessibleObject());
		setupText();
		setupShowText();
		setupAction();
		setupGroup();
	}

	private void setupText() {
		String text = fieldAnnotation.getValue(TEXT_ELEMENT);
		text = isEmpty(text) ? getFieldName() : text;
		setText(text);
	}

	private void setupShowText() {
		boolean show = fieldAnnotation.getValue(SHOW_TEXT_ELEMENT);
		setShowText(show);
	}

	private void setupAction() {
		ActionListener action = (ActionListener) annotationClass.forAttribute(
				ACTION_ELEMENT).build();
		if (action == null) {
			return;
		}
		if (action instanceof Action) {
			setAction((Action) action);
		} else {
			addActionListener(action);
		}
	}

	private void setupGroup() {
		ButtonGroup group = (ButtonGroup) annotationClass.forAttribute(
				GROUP_ELEMENT).build();
		if (group != null) {
			setButtonGroup(group);
		}
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		updateTextsResources();
	}

	/**
	 * Sets the text of the check-box. Defaults to the empty string which means
	 * the field name is used as the text.
	 * <p>
	 * The text can also be a resource name that is queried in the supplied
	 * texts resource.
	 * 
	 * @param text
	 *            the text.
	 */
	public void setText(String text) {
		textResource = text;
		this.text = text;
		updateTextResource();
		log.textSet(this, text);
	}

	/**
	 * Returns the text of the check-box.
	 * 
	 * @return the text.
	 */
	public String getText() {
		return text;
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		updateTextsResources();
	}

	private void updateTextsResources() {
		updateTextResource();
	}

	private void updateTextResource() {
		if (haveTextResource(textResource)) {
			text = getTextResource(textResource);
		}
		if (showText) {
			getComponent().setText(text);
		} else {
			getComponent().setText("");
		}
	}

	/**
	 * Sets if the text of the check-box should be visible or not. Defaults to
	 * {@code true} which means that the text should be visible.
	 * 
	 * @param show
	 *            {@code true} for show the text or {@code false} to not show.
	 */
	public void setShowText(boolean show) {
		this.showText = show;
		updateTextResource();
		log.showTextSet(this, show);
	}

	/**
	 * Returns if the text is showing or not.
	 * 
	 * @return {@code true} for show the text or {@code false} to not show.
	 */
	public boolean getShowText() {
		return showText;
	}

	/**
	 * Adds the action listener to the radio button.
	 * 
	 * @param listener
	 *            the {@link ActionListener}.
	 * 
	 * @throws NullPointerException
	 *             if the specified listener is {@code null}.
	 */
	public void addActionListener(ActionListener listener) {
		log.checkActionListener(this, listener);
		getComponent().addActionListener(listener);
		log.actionAdded(this, listener);
	}

	/**
	 * Removed the action listener to the radio button.
	 * 
	 * @param listener
	 *            the {@link ActionListener}.
	 * 
	 * @throws NullPointerException
	 *             if the specified listener is {@code null}.
	 */
	public void removeActionListener(ActionListener listener) {
		log.checkActionListener(this, listener);
		getComponent().removeActionListener(listener);
		log.actionRemoved(this, listener);
	}

	/**
	 * Sets the action for the radio button.
	 * 
	 * @param action
	 *            the {@link Action} or {@code null}.
	 */
	public void setAction(Action action) {
		getComponent().setAction(action);
		log.actionSet(this, action);
	}

	/**
	 * Returns the action for the radio button.
	 * 
	 * @return the {@link Action} or {@code null}.
	 */
	public Action getAction() {
		return getComponent().getAction();
	}

	/**
	 * Sets the button group for this radio button. The button is added to the
	 * specified group.
	 * 
	 * @param group
	 *            the {@link ButtonGroup}.
	 * 
	 * @throws NullPointerException
	 *             if the specified group is {@code null}.
	 */
	public void setButtonGroup(ButtonGroup group) {
		log.checkButtonGroup(this, group);
		this.buttonGroup = group;
		group.add(getComponent());
		log.buttonGroupSet(this, group);
	}

	/**
	 * Returns the button group of this radio button.
	 * 
	 * @return the {@link ButtonGroup} or {@code null} if the button does not
	 *         belong to any group.
	 */
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
}
