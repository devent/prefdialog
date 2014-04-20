/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.fieldbutton;

import static org.apache.commons.lang3.Validate.notNull;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractFieldButtonField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class AbstractFieldButtonFieldLogger extends AbstractLogger {

	private static final String ACTION_LISTENER_NULL = "Action listener cannot be null for %s.";
	private static final String SHOW_TEXT_SET = "Show text {} set for {}.";
	private static final String TEXT_SET = "Text '{}' set for {}.";
	private static final String ACTION_ADDED = "Action listener {} add for {}.";
	private static final String ACTION_SET = "Action {} set for {}.";
	private static final String BUTTON_GROUP_NULL = "Button group cannot be null for %s.";
	private static final String BUTTON_GROUP_SET = "Button group {} set for {}.";

	/**
	 * Creates logger for {@link AbstractFieldButtonField}.
	 */
	AbstractFieldButtonFieldLogger() {
		super(AbstractFieldButtonField.class);
	}

	void textSet(AbstractFieldButtonField<?> field, String text) {
		log.debug(TEXT_SET, text, field);
	}

	void showTextSet(AbstractFieldButtonField<?> field, boolean show) {
		log.debug(SHOW_TEXT_SET, show, field);
	}

	void checkActionListener(AbstractFieldButtonField<?> field,
			ActionListener listener) {
		notNull(listener, ACTION_LISTENER_NULL, field);
	}

	void actionAdded(AbstractFieldButtonField<?> field, ActionListener listener) {
		log.debug(ACTION_ADDED, listener, field);
	}

	void actionSet(AbstractFieldButtonField<?> field, Action action) {
		log.debug(ACTION_SET, action, field);
	}

	public void actionRemoved(AbstractFieldButtonField<?> field,
			ActionListener listener) {
		notNull(listener, ACTION_LISTENER_NULL, field);
	}

	void checkButtonGroup(AbstractFieldButtonField<?> field, ButtonGroup group) {
		notNull(group, BUTTON_GROUP_NULL, field);
	}

	void buttonGroupSet(AbstractFieldButtonField<?> field, ButtonGroup group) {
		log.debug(BUTTON_GROUP_SET, group, field);
	}
}
