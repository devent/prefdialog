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
package com.anrisoftware.prefdialog.fields.table

import javax.inject.Inject

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.Table

/**
 * Bean with table fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TableBean {

    @FieldComponent
    @Table(model = "myTableModel")
    public Object customTableModelField

    @Inject
    public MyTableModel myTableModel

    @FieldComponent
    @Table(modelClass = MyTableModel.class)
    public Object customModelClass

    @FieldComponent
    @Table(modelClass = MyTableModel.class, renderers = "stringDefaultRenderer")
    public Object stringDefaultRendererField

    @FieldComponent
    @Table(modelClass = MyTableModel.class, renderers = ["stringDefaultRenderer", "doubleDefaultRenderer"])
    public Object multipleDefaultRendererField

    @Inject
    public StringDefaultRenderer stringDefaultRenderer

    @Inject
    public DoubleDefaultRenderer doubleDefaultRenderer

    @FieldComponent
    @Table(modelClass = MyTableModel.class, rendererClasses = StringDefaultRenderer.class)
    public Object stringDefaultRendererClass

    @FieldComponent
    @Table(modelClass = MyTableModel.class, rendererClasses = [StringDefaultRenderer.class, DoubleDefaultRenderer.class])
    public Object multipleDefaultRendererClasses
}
