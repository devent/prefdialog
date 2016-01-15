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

import javax.swing.event.ChangeEvent;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event that the focus state of a dock have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FocusChangedEvent extends ChangeEvent {

	private final boolean focus;

	/**
	 * @see ChangeEvent#ChangeEvent(Object)
	 * 
	 * @param source
	 *            the {@link DockWindow} source.
	 * 
	 * @param focus
	 *            if the dock have the focus.
	 */
	public FocusChangedEvent(DockWindow source, boolean focus) {
		super(source);
		this.focus = focus;
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
	 * Returns if the dock have the focus.
	 * 
	 * @return {@code true} if it have the focus.
	 */
	public boolean isFocus() {
		return focus;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("focus", focus).toString();
	}
}
