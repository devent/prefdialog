/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.table;

import java.lang.annotation.Annotation;

import javax.swing.JTable;

import com.anrisoftware.prefdialog.annotations.Table;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Table field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class TableField extends AbstractTableField<JTable> {

    private static final Class<? extends Annotation> ANNOTATION_CLASS = Table.class;

    /**
     * @see TableFieldFactory#create(Object, String)
     */
    @AssistedInject
    TableField(@Assisted Object parentObject, @Assisted String fieldName) {
        super(ANNOTATION_CLASS, new JTable(), parentObject, fieldName);
    }
}
