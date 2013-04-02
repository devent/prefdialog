/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-core.
 *
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.swing.Icon;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.annotations.TextPosition;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Logging messages for {@link AbstractFieldComponent}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AbstractFieldComponentLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link AbstractFieldComponent}.
	 */
	AbstractFieldComponentLogger() {
		super(AbstractFieldComponent.class);
	}

	void nameSet(AbstractFieldComponent<?> field, String name) {
		log.debug("Set name to ``{}'' for field {}.", name, field);
	}

	void titleSet(AbstractFieldComponent<?> field, String title) {
		log.debug("Set title to ``{}'' for field {}.", title, field);
	}

	void showTitleSet(AbstractFieldComponent<?> field, boolean show) {
		log.debug("Set show title to {} for field {}.", show, field);
	}

	void valueSet(AbstractFieldComponent<?> field, Object value) {
		log.debug("Set value to {} for field {}.", value, field);
	}

	void enabledSet(AbstractFieldComponent<?> field, boolean enabled) {
		log.debug("Set enabled to {} for field {}.", enabled, field);
	}

	void widthSet(AbstractFieldComponent<?> field, Number width) {
		log.debug("Set width to {} for field {}.", width, field);
	}

	void showToolTipSet(AbstractFieldComponent<?> field, boolean show) {
		log.debug("Set show tool-tip to {} for {}.", show, field);
	}

	void inputIsValid(AbstractFieldComponent<?> field, boolean valid) {
		log.debug("Input is valid {} for field {}.", valid, field);
	}

	NullPointerException noChildFieldFound(AbstractFieldComponent<?> field,
			String name) {
		NullPointerException ex = new NullPointerException(format(
				"No child field with the name ``%s'' found in the field %s.",
				name, field));
		return ex;
	}

	void checkName(AbstractFieldComponent<?> field, String name) {
		notNull(name, "The name of the field %s cannot be null.", field);
	}

	void checkField(AbstractFieldComponent<?> field,
			FieldComponent<?> childField) {
		notNull(childField, "The child field %s cannot be null.", field);
	}

	void checkWidth(AbstractFieldComponent<?> field, Number width) {
		notNull(width, "The width of the field %s cannot be null.", field);
	}

	void titleResourceMissing(AbstractFieldComponent<?> field, String title) {
		log.warn(
				"Could not find the title resource ``{}'' using it as the literal title for field {}.",
				title, field);
	}

	void toolTipResourceMissing(AbstractFieldComponent<?> field, String text) {
		log.warn(
				"Could not find the tool-tip resource ``{}'' using it as the literal tool-tip for field {}.",
				text, field);
	}

	void checkTextsResource(AbstractFieldComponent<?> field, Texts texts) {
		notNull(texts, "The texts resource for the field %s cannot be null.",
				field);
	}

	void checkImagesResource(AbstractFieldComponent<?> field, Images images) {
		notNull(images, "The images resource for the field %s cannot be null.",
				field);
	}

	void checkLocale(AbstractFieldComponent<?> field, Locale locale) {
		notNull(locale, "The locale for the field %s cannot be null.", field);
	}

	void localeSet(AbstractFieldComponent<?> field, Locale locale) {
		log.debug("Set locale to {} for field {}.", locale, field);
	}

	void titlePositionSet(AbstractFieldComponent<?> field, TextPosition position) {
		log.debug("Set title position to {} for field {}.", position, field);
	}

	void iconSizeSet(AbstractFieldComponent<?> field, IconSize size) {
		log.debug("Set icon size to {} for field {}.", size, field);
	}

	void iconResourceSet(AbstractFieldComponent<?> field, String name) {
		log.debug("Set icon resource to ``{}'' for field {}.", name, field);
	}

	void iconSet(AbstractFieldComponent<?> field, Icon icon) {
		log.debug("Set icon {} for field {}.", icon, field);
	}

	void applyInputs(AbstractFieldComponent<?> field) {
		log.debug("Apply input for field {}.", field);
	}

	IllegalArgumentException errorSetupValue(AbstractFieldComponent<?> field,
			PropertyVetoException e, Object value) {
		return logException(
				new IllegalArgumentException("Error setup value", e),
				"Error setup value '%s' for %s.", value, field);
	}
}
