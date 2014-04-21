/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-api.
 *
 * prefdialog-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import java.io.Serializable;

import javax.swing.table.TableCellRenderer;

/**
 * Returns the column class and the cell renderer for the table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class DefaultTypedTableCellRenderer implements TypedTableCellRenderer,
        Serializable {

    private Class<?> type;

    private TableCellRenderer renderer;

    public DefaultTypedTableCellRenderer() {
    }

    /**
     * Sets the column class type.
     * 
     * @param type
     *            the {@link Class} type.
     */
    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    /**
     * Sets the cell renderer.
     * 
     * @param renderer
     *            the {@link TableCellRenderer}.
     */
    public void setRenderer(TableCellRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public TableCellRenderer getRenderer() {
        return renderer;
    }

}
