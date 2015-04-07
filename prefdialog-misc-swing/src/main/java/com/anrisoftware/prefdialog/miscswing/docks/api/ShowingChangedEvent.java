/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.event.ChangeEvent;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event that the showing state of a dock have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ShowingChangedEvent extends ChangeEvent {

	private final boolean showing;

	/**
	 * @see ChangeEvent#ChangeEvent(Object)
	 * 
	 * @param source
	 *            the {@link DockWindow} source.
	 * 
	 * @param showing
	 *            if the dock is showing or is hidden.
	 */
	public ShowingChangedEvent(DockWindow source, boolean showing) {
		super(source);
		this.showing = showing;
	}

	/**
	 * Returns the dock window.
	 * 
	 * @return the {@link DockWindow}.
	 */
	public DockWindow getDock() {
		return (DockWindow) getSource();
	}

	/**
	 * Returns if the dock is showing or is hidden.
	 * 
	 * @return {@code true} if it is showing.
	 */
	public boolean isShowing() {
		return showing;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("showing", showing).toString();
	}
}
