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
package com.anrisoftware.prefdialog.fields.combobox;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import java.beans.PropertyVetoException;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link AbstractComboBoxField<?>}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class AbstractComboBoxFieldLogger extends AbstractLogger {

	private static final String FIELD = "field";
	private static final String ELEMENTS_SET = "Elements {} set for {}.";
	private static final String TYPE_NOT_SUPPORTED = "Type %s not supported for %s";
	private static final String ELEMENTS_NULL = "Elements cannot be null for %s.";
	private static final String RENDERER_SET = "Combo box renderer {} set for {}.";
	private static final String RENDERER_NULL = "Renderer cannot be null for %s.";
	private static final String MODEL_SET = "Combo box model {} set for {}.";
	private static final String MODEL_NULL = "Model cannot be null for %s.";
	private static final String ERROR_SET_CUSTOM_RENDERER_MESSAGE = "Error set custom renderer for %s";
	private static final String ERROR_SET_CUSTOM_RENDERER = "Error set custom renderer";
	private static final String ERROR_SET_CUSTOM_MODEL_MESSAGE = "Error set custom model for %s";
	private static final String ERROR_SET_CUSTOM_MODEL = "Error set custom model";
	private static final String EDITOR_SET = "Combo box editor {} set for {}.";
	private static final String EDITOR_NULL = "Combo box editor cannot be null for %s.";

	/**
	 * Creates logger for {@link AbstractComboBoxField<?>}.
	 */
	AbstractComboBoxFieldLogger() {
		super(AbstractComboBoxField.class);
	}

	void checkModel(AbstractComboBoxField<?> field, ComboBoxModel<?> model) {
		notNull(model, MODEL_NULL, field);
	}

	void modelSet(AbstractComboBoxField<?> field, ComboBoxModel<?> model) {
		log.debug(MODEL_SET, model, field);
	}

	IllegalArgumentException unsupportedType(AbstractComboBoxField<?> field,
			Object elements) {
		return logException(
				new IllegalArgumentException(format(TYPE_NOT_SUPPORTED,
						elements.getClass(), field)), TYPE_NOT_SUPPORTED,
				elements.getClass(), field.getName());
	}

	void checkElements(AbstractComboBoxField<?> field, Object elements) {
		notNull(elements, ELEMENTS_NULL, field);
	}

	void elementsSet(AbstractComboBoxField<?> field, Object elements) {
		log.debug(ELEMENTS_SET, elements, field);
	}

	void checkRenderer(AbstractComboBoxField<?> field,
			ListCellRenderer<?> renderer) {
		notNull(renderer, RENDERER_NULL, field);
	}

	void rendererSet(AbstractComboBoxField<?> field,
			ListCellRenderer<?> renderer) {
		log.debug(RENDERER_SET, renderer, field);
	}

	ReflectionError errorSetModel(AbstractComboBoxField<?> field,
			PropertyVetoException e) {
		return logException(
				new ReflectionError(ERROR_SET_CUSTOM_MODEL, e).addContextValue(
						FIELD, field), ERROR_SET_CUSTOM_MODEL_MESSAGE,
				field.getName());
	}

	ReflectionError errorSetRenderer(AbstractComboBoxField<?> field,
			PropertyVetoException e) {
		return logException(
				new ReflectionError(ERROR_SET_CUSTOM_RENDERER, e).addContextValue(
						FIELD, field), ERROR_SET_CUSTOM_RENDERER_MESSAGE,
				field.getName());
	}

	void checkEditor(AbstractComboBoxField<?> field, ComboBoxEditor editor) {
		notNull(editor, EDITOR_NULL, field);
	}

	void editorSet(AbstractComboBoxField<?> field, ComboBoxEditor editor) {
		log.debug(EDITOR_SET, editor, field);
	}
}
