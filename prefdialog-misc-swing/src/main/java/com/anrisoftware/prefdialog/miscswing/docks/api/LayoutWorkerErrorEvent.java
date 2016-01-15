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

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event of an error in the layout.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class LayoutWorkerErrorEvent extends LayoutWorkerEvent {

    private final Throwable cause;

    /**
     * @param cause
     *            the {@link Throwable} cause of the error.
     *
     * @see LayoutWorkerEvent#LayoutWorkerEvent(Object, String)
     */
    public LayoutWorkerErrorEvent(Object source, String name, Throwable cause) {
        super(source, name);
        this.cause = cause;
    }

    /**
     * Returns the cause of the error.
     *
     * @return the {@link Throwable} cause.
     */
    public Throwable getCause() {
        return cause;
    }

    private static final String CAUSE_FIELD = "cause";

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(CAUSE_FIELD, cause)
                .appendSuper(super.toString()).toString();
    }
}
