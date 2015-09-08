/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.filechooser;

import static org.apache.commons.lang3.Validate.notNull;

import javax.swing.Icon;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;
import com.anrisoftware.resources.images.api.IconSize;

/**
 * Logging messages for {@link FileChooserField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileChooserFieldLogger extends AbstractLogger {

	private static final String TEXT_SET = "Button text '{}' set for {}.";
	private static final String MODEL_SET = "File chooser model {} set for {}.";
	private static final String MODEL_NULL = "File chooser model can not be null for field %s.";
	private static final String MNEMONIC_SET = "Button mnemonic '{}' set for {}.";
	private static final String ICON_SET = "Button icon {} set for {}.";
	private static final String ICON_RESOURCE_SET = "Button icon resource '{}' set for {}.";
	private static final String ICON_SIZE_SET = "Button icon size {} set for {}.";
	private static final String TITLE_POSITION_SET = "Button text position {} set for {}.";

	/**
	 * Creates logger for {@link FileChooserField}.
	 */
	FileChooserFieldLogger() {
		super(FileChooserField.class);
	}

	void checkModel(FileChooserField field, FileChooserModel model) {
		notNull(model, MODEL_NULL, field);
	}

	void modelSet(FileChooserField field, FileChooserModel model) {
		if (log.isDebugEnabled()) {
			log.debug(MODEL_SET, model, field);
		} else {
			log.info(MODEL_SET, model, field.getName());
		}
	}

	void buttonMnemonicSet(AbstractFieldComponent<?> field, String string) {
		log.debug(MNEMONIC_SET, string, field);
	}

	void buttonTextSet(String text, FileChooserField field) {
		log.debug(TEXT_SET, text, field);
	}

	void buttonTextPositionSet(AbstractFieldComponent<?> field,
			TextPosition position) {
		log.debug(TITLE_POSITION_SET, position, field);
	}

	void buttonIconSizeSet(AbstractFieldComponent<?> field, IconSize size) {
		log.debug(ICON_SIZE_SET, size, field);
	}

	void buttonIconResourceSet(AbstractFieldComponent<?> field, String name) {
		log.debug(ICON_RESOURCE_SET, name, field);
	}

	void buttonIconSet(AbstractFieldComponent<?> field, Icon icon) {
		log.debug(ICON_SET, icon, field);
	}

}
