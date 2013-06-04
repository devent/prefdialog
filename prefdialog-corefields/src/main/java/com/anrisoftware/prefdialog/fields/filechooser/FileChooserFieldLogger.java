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

import static org.apache.commons.lang3.Validate.notNull;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;

/**
 * Logging messages for {@link FileChooserField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileChooserFieldLogger extends AbstractLogger {

	private static final String MODEL_SET = "File chooser model {} set for {}.";
	private static final String MODEL_NULL = "File chooser model can not be null for field %s.";

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
}
