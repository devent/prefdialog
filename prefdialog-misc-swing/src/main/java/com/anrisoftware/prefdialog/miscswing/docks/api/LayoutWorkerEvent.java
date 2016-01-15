/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.util.EventObject;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event of changes in the layout.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class LayoutWorkerEvent extends EventObject {

    private final String name;

    /**
     * @param name
     *            the name of the layout.
     *
     * @see EventObject#EventObject(Object)
     */
    public LayoutWorkerEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    /**
     * Returns the layout name.
     *
     * @return the layout name
     */
    public String getName() {
        return name;
    }

    private static final String NAME_FIELD = "name";

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(NAME_FIELD, name)
                .appendSuper(super.toString()).toString();
    }
}
