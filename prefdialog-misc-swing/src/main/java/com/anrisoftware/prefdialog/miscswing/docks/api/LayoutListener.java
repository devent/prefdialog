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
package com.anrisoftware.prefdialog.miscswing.docks.api;

/**
 * Informed of changes in the layout.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LayoutListener {

	/**
	 * Informed when the layout was saved.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerEvent}.
	 */
	void layoutSaved(LayoutWorkerEvent event);

	/**
	 * Informed when the layout was loaded.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerEvent}.
	 */
	void layoutLoaded(LayoutWorkerEvent event);

	/**
	 * Informed when there was an error.
	 * 
	 * @param event
	 *            the {@link LayoutWorkerErrorEvent}.
	 */
	void layoutError(LayoutWorkerErrorEvent event);

}
