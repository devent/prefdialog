/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.listbox;

import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ELEMENTS_NULL;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ELEMENTS_SET;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ERROR_SET_CUSTOM_MODEL;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ERROR_SET_CUSTOM_MODEL_MESSAGE;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ERROR_SET_CUSTOM_RENDERER;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.ERROR_SET_CUSTOM_RENDERER_MESSAGE;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.FIELD;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.MODEL_NULL;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.MODEL_SET;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.RENDERER_NULL;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.RENDERER_SET;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.TYPE_NOT_SUPPORTED;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.beans.PropertyVetoException;

import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError;
import com.anrisoftware.prefdialog.fields.combobox.AbstractComboBoxField;

/**
 * Logging messages for {@link AbstractComboBoxField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("rawtypes")
class AbstractListBoxFieldLogger extends AbstractLogger {

	enum _ {

		FIELD("field"),

		ELEMENTS_SET("Elements {} set for {}."),

		TYPE_NOT_SUPPORTED("Type %s not supported for %s"),

		ELEMENTS_NULL("Elements cannot be null for %s."),

		RENDERER_SET("Combo box renderer {} set for {}."),

		RENDERER_NULL("Renderer cannot be null for %s."),

		MODEL_SET("Combo box model {} set for {}."),

		MODEL_NULL("Model cannot be null for %s."),

		ERROR_SET_CUSTOM_RENDERER_MESSAGE("Error set custom renderer for %s"),

		ERROR_SET_CUSTOM_RENDERER("Error set custom renderer"),

		ERROR_SET_CUSTOM_MODEL_MESSAGE("Error set custom model for %s"),

		ERROR_SET_CUSTOM_MODEL("Error set custom model");

		private String name;

		private _(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * Creates logger for {@link AbstractComboBoxField}.
	 */
	AbstractListBoxFieldLogger() {
		super(AbstractListBoxField.class);
	}

	void checkModel(AbstractListBoxField field, ListModel model) {
		notNull(model, MODEL_NULL.toString(), field);
	}

	void modelSet(AbstractListBoxField field, ListModel model) {
		debug(MODEL_SET.toString(), model, field);
	}

	void unsupportedType(AbstractListBoxField field, Object elements) {
		isTrue(false, TYPE_NOT_SUPPORTED.toString(), elements.getClass(), field);
	}

	void checkElements(AbstractListBoxField field, Object elements) {
		notNull(elements, ELEMENTS_NULL.toString(), field);
	}

	void elementsSet(AbstractListBoxField field, Object elements) {
		debug(ELEMENTS_SET, elements, field);
	}

	void checkRenderer(AbstractListBoxField field, ListCellRenderer renderer) {
		notNull(renderer, RENDERER_NULL.toString(), field);
	}

	void rendererSet(AbstractListBoxField field, ListCellRenderer renderer) {
		debug(RENDERER_SET, renderer, field);
	}

	ReflectionError errorSetModel(AbstractListBoxField field,
			PropertyVetoException e) {
		return logException(new ReflectionError(ERROR_SET_CUSTOM_MODEL, e).add(
				FIELD, field), ERROR_SET_CUSTOM_MODEL_MESSAGE, field.getName());
	}

	ReflectionError errorSetRenderer(AbstractListBoxField field,
			PropertyVetoException e) {
		return logException(
				new ReflectionError(ERROR_SET_CUSTOM_RENDERER, e).add(FIELD,
						field), ERROR_SET_CUSTOM_RENDERER_MESSAGE,
				field.getName());
	}
}
