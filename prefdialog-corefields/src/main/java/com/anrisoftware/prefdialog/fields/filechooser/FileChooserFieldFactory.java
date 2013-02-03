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

import java.awt.Container;
import java.lang.reflect.Field;

/**
 * Factory to create a new combo box field. A combo box field can only be
 * checked or unchecked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
interface FileChooserFieldFactory {

	/**
	 * Creates a new combo box field for the specified field of the preference
	 * bean object.
	 * 
	 * @param container
	 *            the {@link Container} for the combo box field.
	 * 
	 * @param bean
	 *            the preference bean {@link Object} where the field is defined.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @return the {@link FileChooserField}.
	 */
	FileChooserField create(Container container, Object bean, Field field);
}
