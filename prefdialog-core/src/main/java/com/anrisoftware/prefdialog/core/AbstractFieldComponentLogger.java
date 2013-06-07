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

	private static final String ERROR_SETUP_VALUE_MESSAGE = "Error setup value '%s' for %s.";
	private static final String ERROR_SETUP_VALUE = "Error setup value";
	private static final String ICON_SET = "Icon {} set for {}.";
	private static final String ICON_RESOURCE_SET = "Icon resource '{}' set for {}.";
	private static final String ICON_SIZE_SET = "Icon size {} set for {}.";
	private static final String TITLE_POSITION_SET = "Title position {} set for {}.";
	private static final String LOCALE_SET = "Locale {} set for {}.";
	private static final String LOCALE_NULL = "Locale cannot be null.";
	private static final String IMAGES_RESOURCE_NULL = "Images resource cannot be null.";
	private static final String TEXTS_RESOURCE_NULL = "Texts resource cannot be null.";
	private static final String NO_TOOL_TIP_RESOURCE = "No tool-tip resource '{}' found for {}.";
	private static final String NO_TITLE_RESOURCE = "No title resource '{}' found for {}.";
	private static final String WIDTH_NULL = "Width cannot be null.";
	private static final String FIELD_NULL = "Field cannot be null.";
	private static final String NAME_NULL = "Name cannot be null.";
	private static final String NO_CHILD = "No child '%s' found for %s.";
	private static final String RESTORED_INPUT = "Restored input for {}.";
	private static final String SHOW_TOOL_TIP_SET = "Show tool-tip {} set for {}.";
	private static final String WIDTH_SET = "Width {} set for {}.";
	private static final String ENABLED_SET = "Enabled {} set for {}.";
	private static final String VALUE_SET = "Value {} set for {}.";
	private static final String SHOW_TITLE_SET = "Show title {} set for {}.";
	private static final String TITLE_SET = "Title '{}' set for {}.";
	private static final String NAME_SET = "Name '{}' set for {}.";

	/**
	 * Creates logger for {@link AbstractFieldComponent}.
	 */
	AbstractFieldComponentLogger() {
		super(AbstractFieldComponent.class);
	}

	void nameSet(AbstractFieldComponent<?> field, String name) {
		log.debug(NAME_SET, name, field);
	}

	void titleSet(AbstractFieldComponent<?> field, String title) {
		log.debug(TITLE_SET, title, field);
	}

	void showTitleSet(AbstractFieldComponent<?> field, boolean show) {
		log.debug(SHOW_TITLE_SET, show, field);
	}

	void valueSet(AbstractFieldComponent<?> field, Object value) {
		log.debug(VALUE_SET, value, field);
	}

	void enabledSet(AbstractFieldComponent<?> field, boolean enabled) {
		log.debug(ENABLED_SET, enabled, field);
	}

	void widthSet(AbstractFieldComponent<?> field, Number width) {
		log.debug(WIDTH_SET, width, field);
	}

	void showToolTipSet(AbstractFieldComponent<?> field, boolean show) {
		log.debug(SHOW_TOOL_TIP_SET, show, field);
	}

	void restoredInput(AbstractFieldComponent<?> field) {
		log.debug(RESTORED_INPUT, field);
	}

	NullPointerException noChildFieldFound(AbstractFieldComponent<?> field,
			String name) {
		return logException(
				new NullPointerException(format(NO_CHILD, name, field)),
				NO_CHILD, name, field);
	}

	void checkName(AbstractFieldComponent<?> field, String name) {
		notNull(name, NAME_NULL);
	}

	void checkField(AbstractFieldComponent<?> field,
			FieldComponent<?> childField) {
		notNull(childField, FIELD_NULL);
	}

	void checkWidth(AbstractFieldComponent<?> field, Number width) {
		notNull(width, WIDTH_NULL);
	}

	void titleResourceMissing(AbstractFieldComponent<?> field, String title) {
		log.warn(NO_TITLE_RESOURCE, title, field);
	}

	void toolTipResourceMissing(AbstractFieldComponent<?> field, String text) {
		log.warn(NO_TOOL_TIP_RESOURCE, text, field);
	}

	void checkTextsResource(AbstractFieldComponent<?> field, Texts texts) {
		notNull(texts, TEXTS_RESOURCE_NULL);
	}

	void checkImagesResource(AbstractFieldComponent<?> field, Images images) {
		notNull(images, IMAGES_RESOURCE_NULL);
	}

	void checkLocale(AbstractFieldComponent<?> field, Locale locale) {
		notNull(locale, LOCALE_NULL);
	}

	void localeSet(AbstractFieldComponent<?> field, Locale locale) {
		log.debug(LOCALE_SET, locale, field);
	}

	void titlePositionSet(AbstractFieldComponent<?> field, TextPosition position) {
		log.debug(TITLE_POSITION_SET, position, field);
	}

	void iconSizeSet(AbstractFieldComponent<?> field, IconSize size) {
		log.debug(ICON_SIZE_SET, size, field);
	}

	void iconResourceSet(AbstractFieldComponent<?> field, String name) {
		log.debug(ICON_RESOURCE_SET, name, field);
	}

	void iconSet(AbstractFieldComponent<?> field, Icon icon) {
		log.debug(ICON_SET, icon, field);
	}

	IllegalArgumentException errorSetupValue(AbstractFieldComponent<?> field,
			PropertyVetoException e, Object value) {
		return logException(new IllegalArgumentException(ERROR_SETUP_VALUE, e),
				ERROR_SETUP_VALUE_MESSAGE, value, field);
	}
}
