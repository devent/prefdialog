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
package com.anrisoftware.prefdialog.fields.filechooser;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link FileChooserField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class FileChooserFieldLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link FileChooserField}.
	 */
	FileChooserFieldLogger() {
		super(FileChooserField.class);
	}

	void checkModel(FileChooserField field,
			@SuppressWarnings("rawtypes") ComboBoxModel model) {
		notNull(model, "The model cannot be null for the combo box field %s.",
				field);
	}

	void modelSet(FileChooserField field,
			@SuppressWarnings("rawtypes") ComboBoxModel model) {
		log.trace("Set model {} to the combo box field {}.", model, field);
	}

	IllegalArgumentException unsupportedType(FileChooserField field,
			Object elements) {
		IllegalArgumentException ex = new IllegalArgumentException(
				format("The type %s is not supported as elements of the combo box field %s",
						elements.getClass(), field));
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	void checkElements(FileChooserField field, Object elements) {
		notNull(elements,
				"The elements cannot be null for the combo box field %s.",
				field);
	}

	void elementsSet(FileChooserField field, Object elements) {
		log.trace("Set elements {} for the combo box field {}.", elements,
				field);
	}

	void checkRenderer(FileChooserField field,
			@SuppressWarnings("rawtypes") ListCellRenderer renderer) {
		notNull(renderer,
				"The renderer cannot be null for the combo box field %s.",
				field);
	}

	void rendererSet(FileChooserField field,
			@SuppressWarnings("rawtypes") ListCellRenderer renderer) {
		log.trace("Set renderer {} to the combo box field {}.", renderer, field);
	}
}
