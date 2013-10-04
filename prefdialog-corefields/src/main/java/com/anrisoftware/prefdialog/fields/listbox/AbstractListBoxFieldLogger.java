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

import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.elements_null;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.elements_set;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.error_custom_model;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.error_custom_model_message;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.error_custom_renderer;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.error_custom_renderer_message;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.model_null;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.model_set;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.renderer_null;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.renderer_set;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.type_not_supported;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.vetoed_values;
import static com.anrisoftware.prefdialog.fields.listbox.AbstractListBoxFieldLogger._.vetoed_values_message;
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

		field("field"),

		elements_set("Elements {} set for {}."),

		type_not_supported("Type %s not supported for %s"),

		elements_null("Elements cannot be null for %s."),

		renderer_set("Combo box renderer {} set for {}."),

		renderer_null("Renderer cannot be null for %s."),

		model_set("Combo box model {} set for {}."),

		model_null("Model cannot be null for %s."),

		error_custom_renderer_message("Error set custom renderer for %s"),

		error_custom_renderer("Error set custom renderer"),

		error_custom_model_message("Error set custom model for %s"),

		error_custom_model("Error set custom model"),

		vetoed_values("Values vetoed"),

		vetoed_values_message("Values vetoed for field {}.");

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
		notNull(model, model_null.toString(), field);
	}

	void modelSet(AbstractListBoxField field, ListModel model) {
		debug(model_set.toString(), model, field);
	}

	void unsupportedType(AbstractListBoxField field, Object elements) {
		isTrue(false, type_not_supported.toString(), elements.getClass(), field);
	}

	void checkElements(AbstractListBoxField field, Object elements) {
		notNull(elements, elements_null.toString(), field);
	}

	void elementsSet(AbstractListBoxField field, Object elements) {
		debug(elements_set, elements, field);
	}

	void checkRenderer(AbstractListBoxField field, ListCellRenderer renderer) {
		notNull(renderer, renderer_null.toString(), field);
	}

	void rendererSet(AbstractListBoxField field, ListCellRenderer renderer) {
		debug(renderer_set, renderer, field);
	}

	ReflectionError errorSetModel(AbstractListBoxField field,
			PropertyVetoException e) {
		return logException(
				new ReflectionError(error_custom_model, e).add(field, field),
				error_custom_model_message, field.getName());
	}

	ReflectionError errorSetRenderer(AbstractListBoxField field,
			PropertyVetoException e) {
		return logException(
				new ReflectionError(error_custom_renderer, e).add(field, field),
				error_custom_renderer_message, field.getName());
	}

	void vetoedValues(AbstractListBoxField field, PropertyVetoException e) {
		logException(new ReflectionError(vetoed_values, e).add(field, field),
				vetoed_values_message, field.getName());
	}
}
