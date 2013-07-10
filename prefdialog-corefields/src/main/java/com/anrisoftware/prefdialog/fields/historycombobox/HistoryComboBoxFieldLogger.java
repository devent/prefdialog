/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.historycombobox;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Collection;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link HistoryComboBoxField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class HistoryComboBoxFieldLogger extends AbstractLogger {

	private static final String MAXIMUM_SET = "History maximum {} set for {}.";
	private static final String MAXIMUM_NEGATIVE = "History maximum must not be negative for %s.";
	private static final String HISTORY_SET = "History collection {} set for {}.";
	private static final String HISTORY_NULL = "History collection can not be null for %s.";
	private static final String ITEMS_NULL = "Default items cannot be null for %s.";
	private static final String ITEMS_SET = "Default items {} set for {}.";
	private static final String TYPE_NOT_SUPPORTED = "Type %s not supported for %s";

	/**
	 * Create logger for {@link HistoryComboBoxField}.
	 */
	public HistoryComboBoxFieldLogger() {
		super(HistoryComboBoxField.class);
	}

	void checkDefaultItems(HistoryComboBoxField field, Object elements) {
		notNull(elements, ITEMS_NULL, field);
	}

	void defaultItemsSet(HistoryComboBoxField field, Object elements) {
		log.debug(ITEMS_SET, elements, field);
	}

	IllegalArgumentException unsupportedType(HistoryComboBoxField field,
			Object elements) {
		return logException(
				new IllegalArgumentException(format(TYPE_NOT_SUPPORTED,
						elements.getClass(), field)), TYPE_NOT_SUPPORTED,
				elements.getClass(), field.getName());
	}

	void checkHistory(HistoryComboBoxField field, Collection<?> history) {
		notNull(history, HISTORY_NULL, field);
	}

	void historySet(HistoryComboBoxField field, Collection<?> history) {
		log.debug(HISTORY_SET, history, field);
	}

	void checkMaximum(HistoryComboBoxField field, int maximum) {
		isTrue(maximum > -1, MAXIMUM_NEGATIVE, field);
	}

	void maximumSet(HistoryComboBoxField field, int maximum) {
		log.debug(MAXIMUM_SET, maximum, field);
	}

}
