/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.actions;

import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

/**
 * Menu action.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface MenuAction {

    /**
     * Adds the action to be run.
     * 
     * @param action
     *            the {@link Callable} action.
     * 
     * @param listeners
     *            the {@link PropertyChangeListener} listeners that are informed
     *            when the status of the action have changed.
     */
    void addAction(Callable<?> action, PropertyChangeListener... listeners);

    /**
     * Adds the action to be run on the AWT thread.
     * 
     * @param action
     *            the {@link Runnable} action.
     */
    void addAWTAction(Runnable action);

}
