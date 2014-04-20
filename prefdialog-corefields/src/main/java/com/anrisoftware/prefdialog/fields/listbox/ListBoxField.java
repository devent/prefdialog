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
package com.anrisoftware.prefdialog.fields.listbox;

import java.lang.annotation.Annotation;

import javax.swing.JList;

import com.anrisoftware.prefdialog.annotations.ListBox;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * List box field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ListBoxField extends AbstractListBoxField<JList<?>> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ListBox.class;

	/**
	 * @see ListBoxFieldFactory#create(Object, String)
	 */
	@AssistedInject
	ListBoxField(@Assisted Object parentObject, @Assisted String fieldName) {
		super(ANNOTATION_CLASS, new JList<Object>(), parentObject, fieldName);
	}
}
